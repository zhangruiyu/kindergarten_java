package com.zhangruiyu.github.youeryuanxiaozhushou

class StringInput(var param: String, var failsMessage: String = "") : Input {
    override fun getValue(): String = param

}