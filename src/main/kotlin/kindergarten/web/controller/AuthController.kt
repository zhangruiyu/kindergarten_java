package kindergarten.web.controller

import com.utils.kindergartens.otherwise
import com.utils.kindergartens.yes
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import kindergarten.ext.*
import kindergarten.security.JwtUserFactory
import kindergarten.utils.OCSUtils
import kindergarten.web.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import javax.servlet.http.HttpServletRequest


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
//@RequestMapping(value = "/")
@Api(description = "登陆注册")
class AuthController(
        @Autowired private var mPassportService: AuthService) {
    private val logger = LoggerFactory.getLogger(this.javaClass)


    @PostMapping(value = "/public/auth/login")
    fun login(@RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Callable<ResponseData> {
        logger.debug("试着登陆")
        logger.error("试着登陆")
        logger.info("试着登陆")
        logger.warn("试着登陆")
        logger.trace("试着登陆")
        return Callable {
            (tel.isEmpty() || password.isEmpty()).yes {
                "手机号或者密码不能不填".jsonNormalFail()
            }.otherwise {
                mPassportService.login(tel, password).jsonOk()
            }

        }

    }

    @PostMapping(value = "/public/auth/register1")
    @ApiOperation(value = "注册第一步", notes = "根据手机号和密码注册")
    @ApiImplicitParams(ApiImplicitParam(name = "tel", value = "用户手机号", required = true, dataType = "Long"))
    fun register1(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String): ResponseData {
        return (tel.length > 11).yes {
            "手机号格式错误".jsonNormalFail()
        }.otherwise {
            mPassportService.trySendAuthCode(tel, httpServletRequest.getIpAddr())
        }
    }

    @PostMapping(value = "/public/auth/register2")
    @ApiImplicitParam(name = "tel", value = "用户手机号", required = true)
    fun test(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String
             , @RequestParam(required = true) password: String
             , @RequestParam(required = true) authCode: String): ResponseData {
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
            return if ("888888" == authCode) {
                mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
                "注册成功,请登录".jsonOKNoData()
            } else {
                "验证码错误".jsonNormalFail()
            }

        }
    }

    @PostMapping(value = "/user/profile")
    @ApiImplicitParam(name = "获取用户信息")
    fun getProfile(httpServletRequest: HttpServletRequest): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return mPassportService.getKgProfile(jwt.id).jsonOk()
    }

    @PostMapping(value = "/user/reviseProfile")
    @ApiImplicitParams(ApiImplicitParam(name = "avatarUrl", value = "如果没修改 传入空字符串", required = true, dataType = "String"))
    fun reviseProfile(httpServletRequest: HttpServletRequest, @RequestParam(required = true) checkGender: Int
                      , @RequestParam(required = true) relationCheck: Int,
                      @RequestParam(required = true) address: String,
                      @RequestParam(required = true) avatarUrl: String): ResponseData {
        if (relationCheck < 0 || relationCheck > 6 || checkGender < 0 || checkGender > 1) {
            return "数据格式不对,请联系管理人员".jsonNormalFail()
        }
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return mPassportService.reviseProfile(jwt.id, checkGender, relationCheck, address, OCSUtils.toLocation(avatarUrl)).jsonOk()
    }
}