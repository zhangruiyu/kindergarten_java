package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField

class KgCamera {
    @JSONField(serialize = false)
    var id: String? = null
    @JSONField(serialize = false)
    var schoolId: String? = null
    @JSONField(serialize = false)
    var classroomId: String? = null
    var deviceSerial: String? = null
    var deviceName: String? = null
    var model: String? = null
    var verifyCode: String? = null
}