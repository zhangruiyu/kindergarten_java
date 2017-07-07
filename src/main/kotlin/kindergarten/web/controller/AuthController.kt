package kindergarten.web.controller

import com.utils.kindergartens.otherwise
import com.utils.kindergartens.yes
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import kindergarten.ext.getIpAddr
import kindergarten.ext.jsonOKNoData
import kindergarten.ext.throwMessageException
import kindergarten.web.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
//@RequestMapping(value = "/")
@Api(description = "登陆注册")
class AuthController(
        @Autowired var mPassportService: AuthService) {

    @PostMapping(value = "/public/auth/login")
    fun login(@RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any? {
        return (tel.isEmpty() || password.isEmpty()).yes {
            "手机号或者密码不能不填".throwMessageException()
        }.otherwise {
            mPassportService.login(tel, password)
        }
    }

    @PostMapping(value = "/public/auth/register1")
    @ApiOperation(value = "注册第一步", notes = "根据手机号和密码注册")
    @ApiImplicitParams(ApiImplicitParam(name = "tel", value = "用户手机号", required = true, dataType = "Long"))
    fun register1(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String): Any {
        return (tel.length > 11).yes {
            "手机号格式错误".throwMessageException()
        }.otherwise {
            mPassportService.trySendAuthCode(tel, httpServletRequest.getIpAddr())
        }
    }

    @PostMapping(value = "/public/auth/register2")
    @ApiImplicitParam(name = "tel", value = "用户手机号", required = true)
    fun test(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String
             , @RequestParam(required = true) password: String
             , @RequestParam(required = true) authCode: String): Any {
        (tel.isEmpty() || password.isEmpty() || authCode.isEmpty()).yes {
            "手机号,密码和验证码不能不填".throwMessageException()
        }.otherwise {
            //从redis里查看验证码
         /*   if (stringRedisTemplate.opsForValue().get("${AuthService.authCodePrefix}:$tel") == authCode) {
                mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
                return "注册成功,请登录".jsonOKNoData()
            } else {
                "验证码错误".throwMessageException()
            }*/
             if ("888888" == authCode) {
                mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
                return "注册成功,请登录".jsonOKNoData()
            } else {
                "验证码错误".throwMessageException()
            }

        }
    }
}