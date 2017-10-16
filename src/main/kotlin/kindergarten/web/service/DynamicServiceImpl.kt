package kindergarten.web.service

import kindergarten.config.cos.OCSConfig
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOKNoData
import kindergarten.security.JwtUser
import kindergarten.web.dao.KgDynamicDao
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.entity.DynamicProfile
import kindergarten.web.entity.WrapperDynamic
import kindergarten.web.entity.custom.DynamicPicUrl
import org.beetl.sql.core.SQLManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.AlternativeJdkIdGenerator

/**
 * Created by zhangruiyu on 2017/7/11.
 */
interface DynamicService {

    fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): WrapperDynamic?
    fun commitDynamicVideo(userId: String, dynamic_content: String, screenshot_server_url: String, video_server_url: String, video_long: String): ResponseData
    fun commitDynamicPic(id: String, dynamic_content: String, urls: List<DynamicPicUrl>): ResponseData
    fun commitComment(id: String, commentContent: String, dynamicId: String, parentCommentId: Int, timePoke: String): ResponseData
}

@Service
class DynamicServiceImpl(@Autowired private val kgProfileDao: KgProfileDao,
                         @Autowired private val dynamicDao: KgDynamicDao,
                         @Autowired private val authService: AuthService,
//                         @Autowired private val sqlManager: SQLManager,
                         @Autowired private val oCSConfig: OCSConfig
) : DynamicService {


    // dynamic_type 默认是0  获取的是班级的动态  1是全校
    override fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): WrapperDynamic? {
        //用户信息
        val profile = kgProfileDao.selectProfile(jwt.id)
        //判断是用校园id还是教室id获取动态
        val selectId = if (dynamic_type == 0) {
            if (profile.classroomId == null) {
                "0"
            } else profile.classroomId
        } else profile.schoolId

        oCSConfig.picPrefix
        val dynamics = dynamicDao.selectDynamic(selectId, dynamic_type, page_index * page_size, page_size)
        /*   dynamics?.forEach {
               dynamic ->
               @Suppress("UNCHECKED_CAST")
               (dynamic["kgDynamicPics"] as List<KgDynamicPics>).forEach {
                   it.picUrl = oCSConfig.picPrefix + it.picUrl
               }
           }*/
//        dynamicDao.selectDynamic(selectId,dynamic_type)
        //只有第一次时候查询
        val selectAllClassUserInfo = if (page_index == 0) kgProfileDao.selectAllClassUserInfo(profile.classroomId!!) else emptyList<DynamicProfile>()
        return WrapperDynamic(selectAllClassUserInfo, dynamics)
    }

    override fun commitDynamicVideo(userId: String, dynamic_content: String, screenshot_server_url: String, video_server_url: String, video_long: String): ResponseData {
        val kgProfile = authService.getKgProfile(userId)
        val dynamicId = AlternativeJdkIdGenerator().generateId().toString()
        dynamicDao.commitDynamic(userId, dynamicId, kgProfile.schoolId, kgProfile.classroomId, dynamic_content, DynamicTypeVideo, 0)
        dynamicDao.commitDynamicVideo(screenshot_server_url, video_server_url, video_long,dynamicId)
        return "动态发布成功".jsonOKNoData()
    }

    override fun commitDynamicPic(id: String, dynamic_content: String, urls: List<DynamicPicUrl>): ResponseData {
        val kgProfile = authService.getKgProfile(id)
        val dynamicId = AlternativeJdkIdGenerator().generateId().toString()
        dynamicDao.commitDynamic(id, dynamicId, kgProfile.schoolId, kgProfile.classroomId, dynamic_content, DynamicTypePic, 0)
        val sql = "INSERT INTO kg_dynamic_pics (dynamic_id,pic_url, sequence) VALUES ${
        urls.joinToString {
            "($dynamicId,'${it.url}',${it.position})"
        }
        } "
//        sqlManager.executeUpdate(SQLReady(sql))
        return "动态发布成功".jsonOKNoData()
    }

    //提交评论
    override fun commitComment(id: String, commentContent: String, dynamicId: String, parentCommentId: Int, timePoke: String): ResponseData {
        dynamicDao.commitComment(id, commentContent, dynamicId, parentCommentId, timePoke)
        return "评论成功".jsonOKNoData()
    }

    companion object {
        const val DynamicTypePic = 0
        const val DynamicTypeVideo = 1
    }
}