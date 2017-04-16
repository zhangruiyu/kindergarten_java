package com.kindergarten.entity

/**
 * Created by zhangruiyu on 2017/4/16.
 */
import java.io.Serializable
import java.math.*
import java.util.Date
import java.sql.Timestamp

/*
*
* gen by beetlsql 2017-04-16
*/
class TPassport : Serializable {
    var passportId: Int? = null
    var loginCount: Int? = null
    var lastLoginIp: String? = null
    var loginPassword: String? = null
    var registerIp: String? = null
    var tel: String? = null
    var wxOpenId: String? = null
    var lastLoginTime: Date? = null
    var registerTime: Date? = null

    constructor(passportId: Int?, loginCount: Int?, lastLoginIp: String, loginPassword: String, registerIp: String, tel: String, wxOpenId: String, lastLoginTime: Date, registerTime: Date) {
        this.passportId = passportId
        this.loginCount = loginCount
        this.lastLoginIp = lastLoginIp
        this.loginPassword = loginPassword
        this.registerIp = registerIp
        this.tel = tel
        this.wxOpenId = wxOpenId
        this.lastLoginTime = lastLoginTime
        this.registerTime = registerTime
    }

    constructor() {}


}