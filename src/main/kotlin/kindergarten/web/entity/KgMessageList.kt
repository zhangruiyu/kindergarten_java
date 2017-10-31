package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonFormat
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
    var createTime: Date? = null


}
