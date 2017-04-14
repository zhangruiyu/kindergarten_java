package com.kindergarten

/**
 * Created by zhangruiyu on 2017/4/12.
 */
import com.kindergarten.extensions.toString

fun main(args: Array<String>) {
    var ii : String? = null
    println(ii.toString())
   var result = try {
//        1/0
       1+1
   } catch (e:Exception){
       throw IllegalStateException(e)
   }
    print(result)
}