package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import org.beetl.sql.core.TailBean
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamic : TailBean() {
    var id: Int? = null
    @JSONField(serialize = false)
    var schoolId: Int? = null
    @JSONField(serialize = false)
    var classroomId: Int? = null
    @JSONField(serialize = false)
    var visibilityType: Int = 0 //0是班级 1是全园
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")

    var createTime: Date? = null
    var content: String? = null
    var userId: Int? = null //应该再查询用户的昵称
    var nickName: String? = null //应该再查询用户的昵称
    //    @JSONField(serialize = false)
//    var kgDynamicPics: List<KgDynamicPics>? = null
}

class WrapperDynamic(var allClassRoomUserInfo: List<DynamicProfile>, var dynamics: List<KgDynamic>)

class DynamicProfile {
    var userId: String? = null
    var nickName: String? = null
}