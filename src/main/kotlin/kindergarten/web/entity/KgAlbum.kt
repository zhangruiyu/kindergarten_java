package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants
import kindergarten.custom.CustomTailBean
import kindergarten.utils.OCSUtils
import java.util.*

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgAlbum : CustomTailBean() {
    @JsonIgnore
    var id: String? = null
    var picUrl: String? = null
        set(value) {
            field = OCSUtils.getPicUrl(value)
        }
    var sequence: Int? = null
    var createTime: Date? = null
}