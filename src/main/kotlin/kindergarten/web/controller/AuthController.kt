package kindergarten.web.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import kindergarten.custom.CustomConstants
import kindergarten.ext.getIpAddr
import com.utils.kindergartens.otherwise
import kindergarten.ext.throwMessageException
import com.utils.kindergartens.yes
import kindergarten.ext.jsonOKNoData
import kindergarten.web.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
@RequestMapping(value = "/auth")
@Api(description = "登陆注册")
class AuthController(@Autowired val stringRedisTemplate: StringRedisTemplate) {
    @Autowired
    lateinit var mPassportService: AuthService

    @RequestMapping(value = "/login")
    fun login(@RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any? {
        return (tel.isEmpty() || password.isEmpty()).yes {
            "手机号或者密码不能不填".throwMessageException()
        }.otherwise {
            mPassportService.login(tel, password)
        }
    }

    @RequestMapping(value = "/register1")
    @ApiOperation(value = "注册第一步", notes = "根据手机号和密码注册")
    @ApiImplicitParams(ApiImplicitParam(name = "tel", value = "用户手机号", required = true, dataType = "Long"))
    fun register1(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String): Any {
        return (tel.length > 11).yes {
            "手机号格式错误".throwMessageException()
        }.otherwise {
            mPassportService.trySendAuthCode(tel, httpServletRequest.getIpAddr())
        }
    }

    @RequestMapping(value = "/register2")
    @ApiImplicitParam(name = "tel", value = "用户手机号", required = true)
            //    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun test(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String
             , @RequestParam(required = true) password: String
             , @RequestParam(required = true) authCode: String): Any {
        (tel.isEmpty() || password.isEmpty() || authCode.isEmpty()).yes {
            "手机号,密码和验证码不能不填".throwMessageException()
        }.otherwise {
            //从redis里查看验证码
            if (stringRedisTemplate.opsForValue().get("${AuthService.authCodePrefix}:$tel") == authCode) {
                mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
                return "注册成功,请登录".jsonOKNoData()
            } else {
                "验证码错误".throwMessageException()
            }

        }
    }
}