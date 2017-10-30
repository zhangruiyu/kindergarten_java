package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.custom.CustomTailBean
import java.io.Serializable

class KgCamera : Serializable, CustomTailBean() {
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
    var isEncrypt: String? = null
}