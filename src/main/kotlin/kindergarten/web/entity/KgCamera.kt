package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore

class KgCamera {
    @JsonIgnore
    var id: String? = null
    @JsonIgnore
    var schoolId: String? = null
    @JsonIgnore
    var classroomId: String? = null
    var deviceSerial: String? = null
    var deviceName: String? = null
    var model: String? = null
    var verifyCode: String? = null
}