package kindergarten.web.dao

import kindergarten.web.entity.KgDynamic
import kindergarten.web.entity.custom.DynamicPicUrl
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/11.
 */
@Repository
interface KgDynamicDao : BaseMapper<KgDynamic> {
    @SqlStatement(params = "selectId,dynamic_type,offset,max_size")
    fun selectDynamic(selectId: Int?, dynamic_type: Int, offset: Int, max_size: Int): List<KgDynamic>?

    @SqlStatement(params = "userId,schoolId,classroomId,dynamic_content,dynamic_type,visibilityType")
    fun commitDynamic(userId: String, schoolId: Int?, classroomId: Int?, dynamic_content: String, dynamic_type: Int, visibilityType: Int)

    @SqlStatement(params = "screenshot_server_url,video_server_url,video_long")
    fun commitDynamicVideo(screenshot_server_url: String, video_server_url: String, video_long: String)

    @SqlStatement(params = "values")
    fun commitDynamicPic(values: String)
}
