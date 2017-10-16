package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.custom.CustomTailBean
import org.beetl.sql.core.TailBean
import java.io.Serializable


class KgClassroom : Serializable, CustomTailBean() {
    @JsonIgnore
    var id: Int? = null
    var childCount: Int? = null
    //是否是走廊或者通道 走廊通道是1 教室是0
    var isCorridor: Int? = null
    @JsonIgnore
    var schoolId: Int? = null
    var classroomImage: String? = null
    var showName: String? = null


}