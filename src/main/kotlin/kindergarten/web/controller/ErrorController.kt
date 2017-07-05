package kindergarten.web.controller

import kindergarten.custom.MessageException
import kindergarten.ext.throwMessageException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zhangruiyu on 2017/7/5.
 */
@RestController
class ErrorController{
    @PostMapping(value = "/try/login")
    fun tryLogin(){
        "请登录后再试".throwMessageException(MessageException.TRY_LOGIN_CODE)
    }

}