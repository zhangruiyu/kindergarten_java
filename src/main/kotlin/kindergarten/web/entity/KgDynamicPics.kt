package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import kindergarten.comm.vals.CustomConstants

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamicPics {
    @JSONField(serialize = false)
    var id: Int? = null
    @JSONField(serialize = false)
    var dynamicId: String? = null
    var picUrl: String? = null
        set(value) {
            field = CustomConstants.COSURL.picUrl + value
        }
    var sequence: Int? = null
}