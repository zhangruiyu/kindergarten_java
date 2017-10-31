package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.custom.CustomTailBean
import org.beetl.sql.core.TailBean
import java.util.*

/**
 * Created by zhangruiyu on 2017/7/11.
 */
class KgDynamic : CustomTailBean() {
    var id: String? = null
    @JsonIgnore
    var schoolId: Int? = null
    @JsonIgnore
    var classroomId: Int? = null
    @JsonIgnore
    var visibilityType: Int = 0 //0是班级 1是全园
    var createTime: Date? = null
    var content: String? = null
    var userId: Int? = null //应该再查询用户的昵称
    var nickName: String? = null //应该再查询用户的昵称
    var dynamicType: String? = null //动态类型
    //    @JsonIgnore
//    var kgDynamicPics: List<KgDynamicPics>? = null
}

class WrapperDynamic(var allClassRoomUserInfo: List<DynamicProfile>, var dynamics: List<KgDynamic>)

class DynamicProfile {
    var userId: String? = null
    var nickName: String? = null
}