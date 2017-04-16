package com.kindergarten.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment

/**
 * Created by zhangruiyu on 2017/4/16.
 */
class EnvironmentUtils {
    @Autowired
    lateinit  var env : Environment
    companion object {
        @Autowired
        lateinit  var env : Environment

        fun get(): String {
            env!!.defaultProfiles.map { println(it) }
            return "1"
        }
    }
}

fun main(args: Array<String>) {
    EnvironmentUtils.env.activeProfiles.map { println(it) }
}
