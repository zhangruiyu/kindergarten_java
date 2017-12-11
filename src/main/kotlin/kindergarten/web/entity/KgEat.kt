package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

class KgEat : Serializable {
    @JsonIgnore
    var id: Int? = null
    @JsonIgnore
    var schoolId: Int? = null
    var breakfast: String? = null
    var lunch: String? = null
    var supper: String? = null
    var createTime: String? = null
    @JsonIgnore
    var eatUrl: String? = null
    var eatUrls: ArrayList<String> = ArrayList()


}