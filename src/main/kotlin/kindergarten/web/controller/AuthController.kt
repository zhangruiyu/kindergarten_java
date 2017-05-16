package kindergarten.web.controller

import kindergarten.annotation.PoKo
import kindergarten.utils.HttpServletRequestUtils
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
@PoKo class AuthController {
    @Autowired
    lateinit var mPassportService: AuthService

    @RequestMapping(value = "/login")
    fun login(@RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any? {
        if (tel.isEmpty() || password.isEmpty()) {
            throw RuntimeException("手机号或者密码不能不填")
        }
        return mPassportService.login(tel, password)
//        return mSQLManager.selectSingle("tPassport.queryNewUser", null, User_Passport::class.java)
    }

    @RequestMapping(value = "/register1")
    fun register1(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any {
        if (tel.isEmpty() || password.isEmpty()) {
            throw RuntimeException("手机号或者密码不能不填")
        }
        val user_Passport = mPassportService.registerUser(tel, password, HttpServletRequestUtils.getIpAddr(httpServletRequest))
        return user_Passport
    }
    @RequestMapping(value = "/register2")
    @PreAuthorize("hasRole('ADMIN')")
    fun test(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String, @RequestParam(required = true) password: String): Any {
        if (tel.isEmpty() || password.isEmpty()) {
            throw RuntimeException("手机号或者密码不能不填")
        }
        val user_Passport = mPassportService.registerUser(tel, password, HttpServletRequestUtils.getIpAddr(httpServletRequest))
        return user_Passport
    }
}