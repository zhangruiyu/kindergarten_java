package kindergarten.entity

import com.sun.org.apache.xerces.internal.xs.StringList
import java.io.Serializable
import java.sql.Date

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class User_Passport : Serializable {
    var passport_id: Int? = null
    var login_count: Int? = null
    var last_login_ip: String? = null
    var login_password: String? = null
    var register_ip: String? = null
    var tel: String? = null
    var wx_open_id: String? = null
    var last_login_time: Date? = null
    var register_time: Date? = null
    var roles: List<String> = arrayListOf()


}