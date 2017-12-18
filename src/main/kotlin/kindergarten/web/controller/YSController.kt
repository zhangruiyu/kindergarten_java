package kindergarten.web.controller

import io.swagger.annotations.ApiOperation
import kindergarten.comm.rest.ys.RestApi
import kindergarten.comm.rest.ys.entity.DeviceYSPermission
import kindergarten.comm.rest.ys.entity.DeviceYSResource
import kindergarten.comm.rest.ys.entity.YSStatement
import kindergarten.comm.rest.ys.entity.YSToken
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.ext.throwMessageException
import kindergarten.security.JwtUserFactory
import kindergarten.web.dao.KgCameraDao
import kindergarten.web.dao.KgClassroomDao
import kindergarten.web.entity.KgProfile
import kindergarten.web.entity.custom.WrapperInfo
import kindergarten.web.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.util.AlternativeJdkIdGenerator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.WebAsyncTask
import java.util.concurrent.Callable


/**
 * Created by zhangruiyu on 2017/7/18.
 */
@RestController
@RequestMapping(value = "user/normal/ys")
class YSController(val restApi: RestApi, val authService: AuthService, val kgCameraDao: KgCameraDao, val kgClassroomDao: KgClassroomDao) {

    @Value("\${ys.account.prefix}")
    lateinit var prefix: String
    val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(value = "/registerAndGenerateToken")
    @ApiOperation(value = "注册子账户到萤石云", notes = "注册子用户到萤石云")
            //    @ApiImplicitParams(ApiImplicitParam(name = "t))
    fun registerAndGenerateToken(): Callable<ResponseData>? {
        return Callable {
            val jwtUserAfterFilter = JwtUserFactory.getJwtUserAfterFilter()
            val kgProfile = authService.getKgProfile(jwtUserAfterFilter.id)
            //如果有萤石账号
            if (kgProfile.ysAccountId.isNullOrEmpty()) {
                if (kgProfile.classroomId.isNullOrEmpty()) {
                    "请加入班级后再次尝试".jsonNormalFail()
                } else {
                    val password = AlternativeJdkIdGenerator().generateId().toString()
                    val username = prefix + jwtUserAfterFilter.username
                    //注册子账号到萤石云
                    val registerYSAccount = restApi.registerYSAccount(username, password)

                    if (registerYSAccount.code == 200) {
                        kgProfile.ysAccountId = registerYSAccount.data!!.accountId
                        kgProfile.ysRegisterPassword = password
                        authService.updateKgProfile(kgProfile)
                    }
                    //设置权限
                    kgProfile.ysAccountId?.let {
                        val selectAllFiled = kgCameraDao.selectAllFiled(kgProfile.classroomId!!)
                        //遍历教室摄像头 并赋予权限
                        val statements = selectAllFiled.map {
                            YSStatement(DeviceYSPermission().addPatriarchPermission(), DeviceYSResource().addDev(it.deviceSerial!!))
                        }.toList()
                        restApi.policySet(it, statements)
                    }
                    generateToken(kgProfile).jsonOk()
                }
            } else {
                //获取token
                generateToken(kgProfile).jsonOk()
            }

        }
    }

    fun generateToken(kgProfile: KgProfile): YSToken.Data? {
        return if (kgProfile.classroomId.isNullOrEmpty()) {
            "请加入班级后再次尝试".throwMessageException()
        } else {
            val generateToken = restApi.generateToken(kgProfile.ysAccountId!!)
            generateToken.data
        }

    }

    @PostMapping(value = "/classroom/list")
    @ApiOperation(value = "获取教室和走廊", notes = "获取教室和走廊信息")
            //    @ApiImplicitParams(ApiImplicitParam(name = "t))
    fun classroomList(): WebAsyncTask<ResponseData> {
        val callable = Callable {
            val jwtUserAfterFilter = JwtUserFactory.getJwtUserAfterFilter()
            val kgProfile = authService.getKgProfile(jwtUserAfterFilter.id)
            if (kgProfile.schoolId.isNullOrEmpty()) {
                "请入园再次尝试".jsonNormalFail()
            } else {
                val restYSAdminToken = restApi.restYSAdminToken()
                val classroomAndCamera = kgClassroomDao.selectClassroomAndCamera(kgProfile.schoolId!!)
                WrapperInfo(classroomAndCamera, restYSAdminToken).jsonOk()
            }
        }
        val asyncTask = WebAsyncTask(1500, callable)
        asyncTask.onTimeout({
            "请求超时".jsonNormalFail()
        })
        return WebAsyncTask(1500, callable)
    }

}