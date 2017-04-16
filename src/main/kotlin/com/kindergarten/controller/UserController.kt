package com.kindergarten.controller

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired


/**
 * Created by zhangruiyu on 2017/4/7.
 */
@RestController
@EnableAutoConfiguration
open class UserController {
//    var hint: String = ""
//        set(value) {
//            field = value.toUpperCase()
//        }
//    @Autowired
//    private val personMapper: AuthDao? = null
//
//    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
//    fun index(): Profile {
////        val user = Profile(1, "我很笨1111好")
////        return user
//        println(personMapper!!.getById(1))
//        val byId = this.personMapper!!.getById(1)
//        return byId
//    }
//
//    @RequestMapping(value = "/hello", method = arrayOf(RequestMethod.GET))
//    fun index2(): String {
//        return "Hello 1111~11111"
//    }
}