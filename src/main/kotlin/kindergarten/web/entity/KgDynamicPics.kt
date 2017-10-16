package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamicPics {
    @JsonIgnore
    var id: Int? = null
    @JsonIgnore
    var dynamicId: String? = null
    var picUrl: String? = null
        set(value) {
            field = CustomConstants.COSURL.picUrl + value
        }
    var sequence: Int? = null
}