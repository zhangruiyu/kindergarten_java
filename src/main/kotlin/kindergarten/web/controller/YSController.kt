package kindergarten.web.controller

import io.swagger.annotations.ApiOperation
import kindergarten.comm.rest.RestApi
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOKNoData
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.util.AlternativeJdkIdGenerator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

/**
 * Created by zhangruiyu on 2017/7/18.
 */
@RestController
@RequestMapping(value = "user/ys")
class YSController(val restApi: RestApi, val authService: AuthService) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    //1是发表动态图片
    @PostMapping(value = "/register")
    @ApiOperation(value = "注册子账户到萤石云", notes = "注册子用户到萤石云")
//    @ApiImplicitParams(ApiImplicitParam(name = "t))
    fun getCoSSign(): Callable<ResponseData>? {
        return Callable {
            val jwtUserAfterFilter = JwtUserFactory.getJwtUserAfterFilter()
            if (authService.getKgProfile(jwtUserAfterFilter.id).classroomId.isNullOrEmpty()) {
                "请加入班级后再次尝试".jsonNormalFail()
            } else {
                restApi.registerYSAccount(jwtUserAfterFilter.username, AlternativeJdkIdGenerator().generateId().toString())
                "开通成功".jsonOKNoData()
            }

        }
    }

}