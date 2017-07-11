package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import org.beetl.sql.core.TailBean
import java.util.*

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamic : TailBean() {
    @JSONField(serialize = false)
    var id: Int? = null
    @JSONField(serialize = false)
    var schoolId: Int? = null
    @JSONField(serialize = false)
    var classroomId: Int? = null
    var content: String? = null
    var visibilityType: Int = 0 //0是班级 1是全园
    @JSONField(format = "yyyy-MM-dd HH:mm")
    var createTime: Date? = null
//    @JSONField(serialize = false)
    var userId: Int? = null //应该再查询用户的昵称
//    var kgDynamicPics: List<KgDynamicPics>? = null
}