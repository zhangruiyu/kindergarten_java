package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants
import java.io.Serializable
import java.util.*

/*
*
* gen by beetlsql 2017-06-20
*/
class KgProfile : Serializable {
    @JsonIgnore
    var genderId: Int? = null
    @JsonIgnore
    var loginCount: Int? = null
    @JsonIgnore
    var userId: String? = null
    var schoolId: String? = null
    var classroomId: String? = null
    @JsonIgnore
    var address: String? = null
    var avatar: String? = null
        set(value) {
            if(field?.isNotEmpty()?:false) {
                field = CustomConstants.COSURL.picUrl + value
            }
        }
    @JsonIgnore
    var lastLoginIp: String? = null
    @JsonIgnore
    var realName: String? = null
    @JsonIgnore
    var registerIp: String? = null
    @JsonIgnore
    var tel: String? = null
    @JsonIgnore
    var addTime: Date? = null
    @JsonIgnore
    var birthday: Date? = null
    @JsonIgnore
    var lastLoginTime: Date? = null
    @JsonIgnore
    var registerTime: Date? = null
    var nickName: String? = null
    var ysRegisterPassword: String? = null
    var ysAccountId: String? = null


}
