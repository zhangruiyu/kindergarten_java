package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.util.*

class KgEat : Serializable {
    @JsonIgnore
    var id: Int? = null
    @JsonIgnore
    var schoolId: Int? = null
    var breakfast: String? = null
    var lunch: String? = null
    var supper: String? = null
    @JsonIgnore
    var createTime: Date? = null


}