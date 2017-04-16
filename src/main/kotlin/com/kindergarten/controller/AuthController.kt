package com.kindergarten.controller

import com.kindergarten.service.PassportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import java.sql.Date
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import com.kindergarten.entity.TPassport
import org.beetl.sql.core.*
import org.beetl.sql.core.db.MySqlStyle
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment;
import java.lang.Exception
import javax.sql.DataSource


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
        val source = ConnectionSourceHelper.getSimple("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/kindergarten?useUnicode=true&characterEncoding=utf-8&useSSL=true&verifyServerCertificate=false", "root", "woaiwojia520")
        val mysql = MySqlStyle()
// sql语句放在classpagth的/sql 目录下
        val loader = ClasspathLoader("/sql")
// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        val nc = UnderlinedNameConversion()
// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        val sqlManager = SQLManager(mysql, loader, source, nc, arrayOf(myInterceptor()))

        sqlManager.genPojoCodeToConsole("t_passport");
        sqlManager.genSQLTemplateToConsole("t_passport");

        val insert = sqlManager.insert(TPassport(45,11,"woaiwojia","132131","111","152012341","112",Timestamp(12313123321),Timestamp(12313123321)))

        print(insert)
//
//        return mPassportService.findByPassport_id(user).toString()
        return 1
    }
    class myInterceptor:Interceptor{
        override fun exception(ctx: InterceptorContext?, ex: Exception?) {
            println(ctx.toString())
        }

        override fun before(ctx: InterceptorContext?) {
            println(ctx.toString())
        }

        override fun after(ctx: InterceptorContext?) {
            println(ctx.toString())
        }

    }
}