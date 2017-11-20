package kindergarten.web.service

import kindergarten.config.cos.OCSConfig
import kindergarten.config.redis.RedisUtil
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOKNoData
import kindergarten.security.JwtUser
import kindergarten.web.dao.KgDynamicDao
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.entity.*
import kindergarten.web.entity.custom.DynamicPicUrl
import org.beetl.sql.core.SQLManager
import org.beetl.sql.core.SQLReady
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.AlternativeJdkIdGenerator
import java.util.*

/**
 * Created by zhangruiyu on 2017/7/11.
 */
interface DynamicService {

    fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): Any?
    fun commitDynamicVideo(userId: String, dynamic_content: String, screenshot_server_url: String, video_server_url: String, video_long: String): ResponseData
    fun commitDynamicPic(id: String, dynamic_content: String, urls: List<DynamicPicUrl>): ResponseData
    fun commitComment(id: String, commentContent: String, dynamicId: String, parentCommentId: String, timePoke: String): KgDynamicComment
    fun commitLiked(id: String, dynamicId: String): ResponseData
}

@Service
class DynamicServiceImpl(@Autowired private val kgProfileDao: KgProfileDao,
                         @Autowired private val dynamicDao: KgDynamicDao,
                         @Autowired private val authService: AuthService,
                         @Autowired private val sqlManager: SQLManager,
                         @Autowired private val oCSConfig: OCSConfig,
                         @Autowired private val redisUtil: RedisUtil
) : DynamicService {


    // dynamic_type 默认是0  获取的是班级的动态  1是全校
    override fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): Any? {
        //用户信息
        val profile = kgProfileDao.selectProfile(jwt.id)
        //判断是用校园id还是教室id获取动态
        val selectId = if (dynamic_type == 0) {
            if (profile.classroomId == null) {
                "0"
            } else profile.classroomId
        } else profile.schoolId

        oCSConfig.picPrefix
        val dynamic = dynamicDao.selectDynamic(selectId, dynamic_type, page_index * page_size, page_size).groupBy {
            it.id
        }.values.map { item ->
            val kgDynamic = item[0]
            val commentList = ArrayList<KgDynamicComment>()
            //根据评论的的外键id再次分组,得到就是每个动态的所有评论的集合,再组成对应的评论list
            item.groupBy { it.commentDynamicId }.values.map { commentDynamicList ->
                commentDynamicList.distinctBy { it.commentId }.sortedBy {
                    it.commentCreateTime
                }.forEach { commentDynamic ->
                    try {
                        commentList.add(KgDynamicComment(commentDynamic.commentId!!, commentDynamic.commentUserId!!, commentDynamic.commentDynamicId!!, commentDynamic.commentContent!!
                                , commentDynamic.commentCreateTime!!, commentDynamic.groupTag!!, commentDynamic.parentCommentId!!))
                    } catch (e: Exception) {
                        //没有查询到此消息的评论
                    }
                }
            }
            if (kgDynamic.dynamicType == DynamicTypePic.toString()) {
                val picList = ArrayList<KgDynamicPic>()
                item.distinctBy {
                    it.picUrl
                }.forEachIndexed { index, kgDynamic ->
                    picList.add(KgDynamicPic(kgDynamic.picUrl!!, kgDynamic.sequence!!))
                }
                kgDynamic.set("kgDynamicPics", picList)
            } else {
                item.distinctBy {
                    it.picUrl
                }.forEachIndexed { index, kgDynamic ->
                    kgDynamic.set("kgDynamicVideo", KgDynamicVideo(kgDynamic.picUrl!!, kgDynamic.videoLength!!, kgDynamic.videoUrl!!))
                }

            }
            kgDynamic.set("kgDynamicComment", commentList)
            kgDynamic.set("kgDynamicLiked", redisUtil.rangeAll("kgDynamic:liked:${kgDynamic.id}"))
            kgDynamic
        }
        //只有第一次时候查询
        val selectAllClassUserInfo = if (page_index == 0) kgProfileDao.selectAllClassUserInfo(profile.classroomId!!) else emptyList<DynamicProfile>()
        return WrapperDynamic(selectAllClassUserInfo, dynamic)
    }

    override fun commitDynamicVideo(userId: String, dynamic_content: String, screenshot_server_url: String, video_server_url: String, video_long: String): ResponseData {
        val kgProfile = authService.getKgProfile(userId)
        val dynamicId = AlternativeJdkIdGenerator().generateId().toString()
        dynamicDao.commitDynamic(userId, dynamicId, kgProfile.schoolId, kgProfile.classroomId, dynamic_content, DynamicTypeVideo, 0)
        dynamicDao.commitDynamicVideo(screenshot_server_url, video_server_url, video_long, dynamicId)
        return "动态发布成功".jsonOKNoData()
    }

    override fun commitDynamicPic(id: String, dynamic_content: String, urls: List<DynamicPicUrl>): ResponseData {
        val kgProfile = authService.getKgProfile(id)
        val dynamicId = AlternativeJdkIdGenerator().generateId().toString()
        dynamicDao.commitDynamic(id, dynamicId, kgProfile.schoolId, kgProfile.classroomId, dynamic_content, DynamicTypePic, 0)
        val sql = "INSERT INTO kg_dynamic_content (dynamic_id,pic_url, sequence) VALUES ${
        urls.joinToString {
            "('$dynamicId','${it.url}',${it.position})"
        }
        } "
        sqlManager.executeUpdate(SQLReady(sql))
        return "动态发布成功".jsonOKNoData()
    }

    //提交评论
    override fun commitComment(id: String, commentContent: String, dynamicId: String, parentCommentId: String, timePoke: String): KgDynamicComment {
        val commentId = AlternativeJdkIdGenerator().generateId().toString()
        dynamicDao.commitComment(commentId, id, commentContent, dynamicId, parentCommentId, timePoke)
        return KgDynamicComment(commentId, id, dynamicId, commentContent, Date(), timePoke, parentCommentId.toString())
    }

    override fun commitLiked(id: String, dynamicId: String): ResponseData {
//        dynamicDao.commitLiked(id, dynamicId)
        try {
            redisUtil.zAdd("kgDynamic:liked:$dynamicId", id, System.currentTimeMillis().toDouble())
        } catch (e: Exception) {
            "点赞失败".jsonNormalFail()
        }
        //上来说我不睡觉搁那装鬼 不冲我我不会冲你
        return "".jsonOKNoData()
    }

    companion object {
        const val DynamicTypePic = 0
        const val DynamicTypeVideo = 1
    }
}