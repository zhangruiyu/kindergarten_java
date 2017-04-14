package com.kindergarten

import com.kindergarten.entity.Profile
import com.kindergarten.mapper.AuthMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
//@EnableConfigurationProperties(Profile::class) //从application.properties添加属性的类
class KindergartenApplication: CommandLineRunner {
    @Autowired
    private val personMapper: AuthMapper? = null
    override fun run(vararg args: String?) {
        System.out.println(this.personMapper!!.getById(1))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(KindergartenApplication::class.java, *args)
}
