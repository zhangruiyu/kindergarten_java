package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants
import kindergarten.utils.OCSUtils

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamicPic() {
    constructor(picUrl: String, sequence: Int) : this() {
        this.picUrl = picUrl
        this.sequence = sequence
    }

    @JsonIgnore
    var id: Int? = null
    @JsonIgnore
    var dynamicId: String? = null
    var picUrl: String? = null
        set(value) {
            field = OCSUtils.getPicUrl(value)
        }
    var sequence: Int? = null
}

class KgDynamicVideo {
    constructor(videoPic: String, videoLength: String, videoUrl: String) {
        this.videoPic = videoPic
        this.videoUrl = videoUrl
        this.videoLength = videoLength
    }

    @JsonIgnore
    var id: Int? = null
    @JsonIgnore
    var dynamicId: String? = null
    var videoPic: String? = null
        set(value) {
            field = OCSUtils.getPicUrl(value)
        }
    var videoUrl: String? = null
        set(value) {
            field = OCSUtils.getPicUrl(value)
        }
    var videoLength: String? = null
}