package kindergarten.web.service

import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOKNoData
import kindergarten.security.JwtUser
import kindergarten.web.dao.KgDynamicDao
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.entity.custom.DynamicPicUrl
import kindergarten.web.entity.custom.LastIsertId
import org.beetl.sql.core.SQLManager
import org.beetl.sql.core.SQLReady
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/11.
 */
interface DynamicService {

    fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): Any?
    fun commitDynamicVideo(userId: String, dynamic_content: String, screenshot_server_url: String, video_server_url: String, video_long: String): ResponseData
    fun commitDynamicPic(id: String, dynamic_content: String, urls: List<DynamicPicUrl>): ResponseData
}

@Service
class DynamicServiceImpl(@Autowired private val kgProfileDao: KgProfileDao,
                         @Autowired private val dynamicDao: KgDynamicDao,
                         @Autowired private val authService: AuthService,
                         @Autowired val sqlManager: SQLManager
) : DynamicService {

    // dynamic_type 默认是0  获取的是班级的动态  1是全校
    override fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): Any? {
        //用户信息
        val profile = kgProfileDao.selectProfile(jwt.id)
        //判断是用校园id还是教室id获取动态
        val selectId = if (dynamic_type == 0) {
            if (profile.classroomId == null) {
                0
            } else profile.classroomId
        } else profile.schoolId

//        dynamicDao.selectDynamic(selectId,dynamic_type)
        return dynamicDao.selectDynamic(selectId, dynamic_type, page_index * page_size, page_size)
    }

    override fun commitDynamicVideo(userId: String, dynamic_content: String, screenshot_server_url: String, video_server_url: String, video_long: String): ResponseData {
        val kgProfile = authService.getKgProfile(userId)
        dynamicDao.commitDynamic(userId, kgProfile.schoolId, kgProfile.classroomId, dynamic_content, DynamicTypeVideo, 0)
        dynamicDao.commitDynamicVideo(screenshot_server_url, video_server_url, video_long)
        return "动态发布成功".jsonOKNoData()
    }

    override fun commitDynamicPic(id: String, dynamic_content: String, urls: List<DynamicPicUrl>): ResponseData {
        val kgProfile = authService.getKgProfile(id)
        val commitDynamic = dynamicDao.commitDynamic(id, kgProfile.schoolId, kgProfile.classroomId, dynamic_content, DynamicTypePic, 0)
        val dynamicId = sqlManager.execute(SQLReady("SELECT LAST_INSERT_ID()"), LastIsertId::class.java)[0]["lastInsertId()"]
        //随便返回结果
//        sqlManager.insert(SQLReady(sql), LastIsertId::class.java)
        val values = urls.map {
            "($dynamicId,'${it.url}',${it.position})"
        }.joinToString()
        dynamicDao.commitDynamicPic(values)
        return "动态发布成功".jsonOKNoData()
    }

    companion object {
        const val DynamicTypePic = 0
        const val DynamicTypeVideo = 1
    }
}