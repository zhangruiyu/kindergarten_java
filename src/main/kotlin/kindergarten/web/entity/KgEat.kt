package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants
import kindergarten.utils.OCSUtils
import java.io.Serializable

class KgEat : Serializable {
    @JsonIgnore
    var id: String? = null
    @JsonIgnore
    var schoolId: Int? = null
    var breakfast: String? = null
    var lunch: String? = null
    var supper: String? = null
    var createTime: String? = null
    @JsonIgnore
    var eatUrl: String? = null
        set(value) {
            field = OCSUtils.getPicUrl(value)
        }
    var eatUrls: ArrayList<String> = ArrayList()


}