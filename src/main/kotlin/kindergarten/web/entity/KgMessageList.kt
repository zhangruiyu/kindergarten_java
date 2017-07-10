package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import java.io.Serializable
import java.util.*

/*
*
* gen by beetlsql 2017-07-03
*/
class KgMessageList : Serializable {
    var id: Int? = null
    var schoolId: Int? = null
    var message: String? = null
    @JSONField(format = "yyyy-MM-dd HH:mm")
    var createTime: Date? = null


}
