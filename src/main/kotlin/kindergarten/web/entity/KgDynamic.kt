package kindergarten.web.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import kindergarten.comm.vals.CustomConstants
import kindergarten.custom.CustomTailBean
import kindergarten.utils.OCSUtils
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
    var dynamicType: String? = null //动态类型
    @JsonIgnore
    var picUrl: String? = null
    @JsonIgnore
    var sequence: Int? = null
    @JsonIgnore
    var commentId: String? = null
    @JsonIgnore
    var commentContent: String? = null
    @JsonIgnore
    var groupTag: String? = null
    @JsonIgnore
    var parentCommentId: String? = null
    @JsonIgnore
    var commentUserId: String? = null
    @JsonIgnore
    var commentCreateTime: Date? = null
    @JsonIgnore
    var videoLength: String? = null
    @JsonIgnore
    var commentDynamicId: String? = null
    @JsonIgnore
    var videoUrl: String? = null
    //    @JsonIgnore
//    var kgDynamicPics: List<KgDynamicContent>? = null
}

class WrapperDynamic(var allClassRoomUserInfo: List<DynamicProfile>, var dynamics: Any)

class DynamicProfile {
    var userId: String? = null
    var nickName: String? = null
    var avatar: String? = null
        set(value) {
            field = OCSUtils.getPicUrl(value)
        }
}