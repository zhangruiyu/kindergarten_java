package kindergarten.web.controller

import kindergarten.config.custom.MessageException
import kindergarten.ext.getIpAddr
import kindergarten.ext.otherwise
import kindergarten.ext.yes
import kindergarten.custom.CustomConstants
import kindergarten.web.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
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
class AuthController {
    @Autowired
    lateinit var mPassportService: AuthService

    @RequestMapping(value = "/login")
    fun login(@RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any? {
        return (tel.isEmpty() || password.isEmpty()).yes {
            throw MessageException("手机号或者密码不能不填")
        }.otherwise {
            mPassportService.login(tel, password)
        }
    }

    @RequestMapping(value = "/register1")
    fun register1(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any {
        return (tel.length > 11 || tel.isEmpty() || password.isEmpty()).yes {
            throw MessageException("手机号或者密码格式错误")
        }.otherwise {
            mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
        }
    }

    @RequestMapping(value = "/permission/register2")
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun test(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any {
        return (tel.isEmpty() || password.isEmpty()).yes {
            throw MessageException("手机号或者密码不能不填")
        }.otherwise {
            mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
        }
    }
}