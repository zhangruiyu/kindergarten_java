package kindergarten.web.entity

import kindergarten.annotation.PoKo
import java.util.*

@PoKo
class KgDynamicComment(var id: String, var userId: String, var dynamicId: String, var commentContent: String,
                       var createTime: Date, var groupTag: String, var parentCommentId: String)