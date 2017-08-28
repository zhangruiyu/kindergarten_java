package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import org.beetl.sql.core.TailBean
import java.io.Serializable


class KgClassroom : Serializable, TailBean() {
    @JSONField(serialize = false)
    var id: Int? = null
    var childCount: Int? = null
    //是否是走廊或者通道 走廊通道是1 教室是0
    var isCorridor: Int? = null
    @JSONField(serialize = false)
    var schoolId: Int? = null
    var classroomImage: String? = null
    var showName: String? = null


}