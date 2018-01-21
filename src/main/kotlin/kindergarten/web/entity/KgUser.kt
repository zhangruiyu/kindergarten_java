package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.custom.CustomTailBean
import java.io.Serializable


/*
*
* gen by beetlsql 2017-06-20
*/
class KgUser : CustomTailBean(), Serializable {
    var id: String? = null
    @JsonIgnore
    var loginCount: Int? = null
    @JsonIgnore
    var loginPassword: String? = null
    var token: String? = null
    var tel: String? = null
    var roleName: String? = null
    var gender: String? = null
    var address: String? = null
    var relation: String? = null
    var schoolName: String? = null
    var qqOpenId: String? = null
    var qqNickName: String? = null
    var wxNickName: String? = null
    var wxOpenId: String? = null
}
