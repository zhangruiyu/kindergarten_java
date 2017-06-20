package kindergarten.web.entity

import java.io.Serializable
import java.math.*
import java.util.Date
import java.sql.Timestamp

/*
*
* gen by beetlsql 2017-06-20
*/
class KgProfile : Serializable {
    var id: Int? = null
    var genderId: Int? = null
    var loginCount: Int? = null
    var userId: Int? = null
    var schoolId: Int? = null
    var address: String? = null
    var avatar: String? = null
    var lastLoginIp: String? = null
    var realName: String? = null
    var registerIp: String? = null
    var tel: String? = null
    var addTime: Date? = null
    var birthday: Date? = null
    var lastLoginTime: Date? = null
    var registerTime: Date? = null


}
