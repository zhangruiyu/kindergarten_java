package kindergarten.web.dao

import kindergarten.web.entity.KgDynamic
import org.beetl.sql.core.annotatoin.Sql
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/11.
 */
@Repository
interface KgDynamicDao : BaseMapper<KgDynamic> {
    @SqlStatement(params = "selectId,dynamic_see_type,offset,max_size")
    fun selectDynamic(selectId: String?, dynamic_see_type: Int, offset: Int, max_size: Int): List<KgDynamic>

    @SqlStatement(params = "userId,dynamicId,schoolId,classroomId,dynamic_content,dynamic_type,visibilityType")
    fun commitDynamic(userId: String, dynamicId: String, schoolId: String?, classroomId: String?, dynamic_content: String, dynamic_type: Int, visibilityType: Int)

    @SqlStatement(params = "screenshot_server_url,video_server_url,video_long,dynamicId")
    fun commitDynamicVideo(screenshot_server_url: String, video_server_url: String, video_long: String, dynamicId: String)

    @Sql(value = "INSERT INTO kg_dynamic_comment (id,user_id,comment_content, dynamic_id, state, parent_comment_id, group_tag) VALUES (?,?,?,?,1,?,?)")
    fun commitComment(id: String, user_id: String, commentContent: String, dynamicId: String, parentCommentId: String, timePoke: String)

    @Sql(value = "INSERT INTO kg_dynamic_liked (user_id,dynamic_id) VALUES (?,?)")
    fun commitLiked(user_id: String, dynamicId: String)

}
