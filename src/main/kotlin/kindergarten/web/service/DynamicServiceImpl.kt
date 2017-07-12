package kindergarten.web.service

import kindergarten.security.JwtUser
import kindergarten.web.dao.KgDynamicDao
import kindergarten.web.dao.KgProfileDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/11.
 */
interface DynamicService {

    fun getDynamic(jwt: JwtUser, dynamic_type: Int, page_index: Int, page_size: Int): Any?
}

@Service
class DynamicServiceImpl(@Autowired val kgProfileDao: KgProfileDao,
                         @Autowired val dynamicDao: KgDynamicDao
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
        return dynamicDao.selectDynamic(selectId, dynamic_type,page_index * page_size ,page_size)
    }
}