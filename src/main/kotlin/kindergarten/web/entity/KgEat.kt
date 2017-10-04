package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import java.io.Serializable
import java.util.Date

class KgEat : Serializable {
    @JSONField(serialize = false)
    var id: Int? = null
    @JSONField(serialize = false)
    var schoolId: Int? = null
    var breakfast: String? = null
    var lunch: String? = null
    var supper: String? = null
    @JSONField(format = "yyyy-MM-dd")
    var createTime: Date? = null


}