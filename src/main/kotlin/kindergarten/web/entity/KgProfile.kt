package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import java.io.Serializable
import java.util.*

/*
*
* gen by beetlsql 2017-06-20
*/
class KgProfile : Serializable {
    @JSONField(serialize = false)
    var genderId: Int? = null
    @JSONField(serialize = false)
    var loginCount: Int? = null
    @JSONField(serialize = false)
    var userId: Int? = null
    var schoolId: Int? = null
    var classroomId: Int? = null
    @JSONField(serialize = false)
    var address: String? = null
    var avatar: String? = null
    @JSONField(serialize = false)
    var lastLoginIp: String? = null
    @JSONField(serialize = false)
    var realName: String? = null
    @JSONField(serialize = false)
    var registerIp: String? = null
    @JSONField(serialize = false)
    var tel: String? = null
    @JSONField(serialize = false)
    var addTime: Date? = null
    @JSONField(serialize = false)
    var birthday: Date? = null
    @JSONField(serialize = false)
    var lastLoginTime: Date? = null
    @JSONField(serialize = false)
    var registerTime: Date? = null
    var nickName: String? = null


}
