package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.dao.KgEatListDao
import kindergarten.web.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@RequestMapping(value = "/user/eat")
@Api(description = "饮食消息")
class EatController(@Autowired private val kgEatListDao: KgEatListDao,
                    @Autowired private var mPassportService: AuthService) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = "/eatList")
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun getEatListByDate(@RequestParam(required = false, defaultValue = "0") index: String): Callable<ResponseData> {
        return Callable {
            if (index.toInt() > 2) {
                "查询往期饮食信息,请联系幼儿园工作人员".jsonNormalFail()
            }
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            kgEatListDao.getEatList(mPassportService.getKgProfile(jwt.id).schoolId!!, index).jsonOk()
        }
    }
}