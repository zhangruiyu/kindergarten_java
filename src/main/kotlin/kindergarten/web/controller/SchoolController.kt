package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants.CustomPermission.CAN_USE_TOKEN_URL
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOk
import kindergarten.security.JwtTokenUtil
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.AuthService
import kindergarten.web.service.EatService
import kindergarten.web.service.SchoolService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@Api(description = "学校信息")
class SchoolController(
        @Autowired private var mPassportService: AuthService,
        @Autowired private var schoolService: SchoolService,
        @Autowired
        private var jwtTokenUtil: JwtTokenUtil
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(CAN_USE_TOKEN_URL + "/schoolInfo")
    fun getSchoolInfo(@RequestParam(required = true) schoolId: String): Callable<ResponseData> {
        return Callable {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            //已经加入学校   应该是要付款
            if (mPassportService.getKgProfile(jwt.id).schoolId == schoolId) {
//                不着急写
            }
            schoolService.getSchoolInfoById(schoolId).jsonOk()
        }
    }

}