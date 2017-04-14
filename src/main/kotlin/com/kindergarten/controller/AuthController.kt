package com.kindergarten.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
@RequestMapping(value = "/users")
open class AuthController {
    @RequestMapping(value = "/{user}")
    fun register(@PathVariable user: Long): Any {
        println(user)
        return user
    }
}