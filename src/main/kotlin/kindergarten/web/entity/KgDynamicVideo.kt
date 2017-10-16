package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamicVideo {
    @JsonIgnore
    var id: Int? = null
    @JsonIgnore
    var dynamicId: String? = null
    var videoPic: String? = null
        set(value) {
            field = CustomConstants.COSURL.picUrl + value
        }
    var videoUrl: String? = null
        set(value) {
            field = CustomConstants.COSURL.picUrl + value
        }
    var videoLength: String? = null
}