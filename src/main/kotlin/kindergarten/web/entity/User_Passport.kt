package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import org.beetl.sql.core.TailBean
import org.beetl.sql.core.annotatoin.AutoID
import java.sql.Timestamp

/**
 * Created by zhangruiyu on 2017/4/21.
 */
open class User_Passport : TailBean {

    @JSONField(serialize = false)
    var passport_id: Int? = null
        @AutoID get
    @JSONField(serialize = false)
    var login_password: String? = null
    @JSONField(serialize = false)
    var tel: String? = null
    @JSONField(serialize = false)
    var wx_open_id: String? = null
    @JSONField(serialize = false)
    var last_password_reset_date: Timestamp? = null
    @JSONField(serialize = false)
    var roles: Array<String> = arrayOf()

    constructor()

    constructor(login_password: String?, tel: String?) {
        this.login_password = login_password
        this.tel = tel
    }

    constructor(passport_id: Int?, login_password: String?, tel: String?, roles: Array<String>) : super() {
        this.passport_id = passport_id
        this.login_password = login_password
        this.tel = tel
        this.roles = roles
    }

    constructor(passport_id: Int?, login_password: String?, tel: String?, wx_open_id: String?, last_password_reset_date: Timestamp?, roles: Array<String>) : super() {
        this.passport_id = passport_id
        this.login_password = login_password
        this.tel = tel
        this.wx_open_id = wx_open_id
        this.last_password_reset_date = last_password_reset_date
        this.roles = roles
    }


}