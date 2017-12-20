package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.beetl.sql.core.TailBean
import java.io.Serializable

/*
*
* gen by beetlsql 2017-06-20
*/
class KgUser : TailBean(), Serializable {
    var id: String? = null
    @JsonIgnore
    var loginCount: Int? = null
    @JsonIgnore
    var loginPassword: String? = null
    @JsonIgnore
    var wxOpenId: String? = null

    var token: String? = null
    var tel: String? = null
    var roleName: String? = null
    var gender: String? = null
    var address: String? = null
    var relation: String? = null

}
