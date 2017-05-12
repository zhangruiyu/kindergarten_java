package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import org.beetl.sql.core.annotatoin.AutoID
import java.io.Serializable
import java.sql.Date

/**
 * Created by zhangruiyu on 2017/4/21.
 */
open class User_Passport : Serializable {

    @JSONField(serialize = false)
    var passport_id: Int? = null
        @AutoID get
    @JSONField(serialize = false)
    var login_count: Int? = null
    @JSONField(serialize = false)
    var last_login_ip: String? = null
    @JSONField(serialize = false)
    var login_password: String? = null
    @JSONField(serialize = false)
    var register_ip: String? = null
    var tel: String? = null
    @JSONField(serialize = false)
    var wx_open_id: String? = null
    @JSONField(serialize = false)
    var last_login_time: Date? = null
    @JSONField(serialize = false)
    var register_time: Date? = null
    @JSONField(serialize = false)
    var roles: List<String> = arrayListOf()

    constructor()
    constructor(login_password: String?, register_ip: String?, tel: String?) {
        this.login_password = login_password
        this.register_ip = register_ip
        this.tel = tel
    }

    constructor(passport_id: Int?, login_count: Int?, last_login_ip: String?, login_password: String?, register_ip: String?, tel: String?, wx_open_id: String?, last_login_time: Date?, register_time: Date?, roles: List<String>) {
        this.passport_id = passport_id
        this.login_count = login_count
        this.last_login_ip = last_login_ip
        this.login_password = login_password
        this.register_ip = register_ip
        this.tel = tel
        this.wx_open_id = wx_open_id
        this.last_login_time = last_login_time
        this.register_time = register_time
        this.roles = roles
    }

}