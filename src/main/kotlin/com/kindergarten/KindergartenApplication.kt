package com.kindergarten

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
//@MapperScan("com.kindergarten.dao")
//@EnableConfigurationProperties(Profile::class) //从application.properties添加属性的类
class KindergartenApplication: CommandLineRunner {
//    @Autowired
//    private val mPersonDao: AuthDao? = null
    override fun run(vararg args: String?) {
//        System.out.println(this.mPersonDao!!.getById(1))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(KindergartenApplication::class.java, *args)
}
