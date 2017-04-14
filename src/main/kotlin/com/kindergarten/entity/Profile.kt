package com.kindergarten.entity

import com.kindergarten.annotation.PoKo
import org.springframework.boot.context.properties.ConfigurationProperties
import java.sql.Date
import java.sql.Timestamp


/**
 * Created by zhangruiyu on 2017/4/8.
 */
//@ConfigurationProperties(prefix = "com.profile")
//@PoKo
data class Profile(var profile_ID: Long, var passport_ID: Long, var tel: String, var real_name: String, var gender_ID: Int
                   , var birthday: Date, var address: String, var avatar: String
                   , var add_time: Timestamp, var school_ID: Int, var wx_open_id: String) {

}
