package kindergarten.web.entity

import kindergarten.annotation.PoKo
import java.util.*

@PoKo
class KgDynamicComment(var id: Long, var userId: Long, var dynamicId: Long, var commentContent: String,
                       var createTime: Date, var groupTag: String, var parentCommentId: String)