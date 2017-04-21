package kindergarten.controller

import kindergarten.service.PassportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
@RequestMapping(value = "/users")
open class AuthController {
    @Autowired
    lateinit var mPassportService: PassportService

//    @GetMapping(value = "/login/{user}")
//    fun register(@PathVariable user: Int): Any {
//        println("哈哈哈哈$user")
//        print(mPassportService.findByPassport_id(user))
////        mProfileServicewq.insert(Profile(1,1,"1","1",1,Date(2015,1,1),"1","1",1,"1","1",Timestamp(1,1,1)))
//        return mPassportService.findByPassport_id(user).toString()
//    }

    @GetMapping(value = "/login/{user}")
    fun register(@PathVariable user: Int): Any {
//        val findByPassport_id = mPassportService.findByPassport_id(user)
//        print(findByPassport_id)
//        return findByPassport_id
//        sql.genPojoCodeToConsole("t_profile")
//        sql.genSQLTemplatesqlToConsole("t_passport")
        val arrayList = ArrayList<String>()
//        tPassport.loginCount = 11101
//        tPassport.loginPassword="woaiwojia5230"
//        tPassport.lastLoginTime = Date(1231312312321)
//        return sqlManagerFactoryBean.`object`.unique(TPassport::class.java, 11)
      return  mPassportService.findByPassport_id(user)
//        return sqlManagerFactoryBean.`object`.selectSingle("user.queryNewUser", null, TPassport::class.java)
    }
}