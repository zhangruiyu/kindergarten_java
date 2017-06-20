package kindergarten.web.entity

import org.beetl.sql.core.TailBean
import java.io.Serializable

/*
*
* gen by beetlsql 2017-06-20
*/
class KgUser : TailBean(), Serializable {
    var id: Int? = null
    var loginCount: Int? = null
    var loginPassword: String? = null
    var tel: String? = null
    var wxOpenId: String? = null
    var roles: Array<String> = arrayOf()

}
