package kindergarten.web.service

import kindergarten.web.dao.KgMessageListDao
import kindergarten.web.entity.KgMessageList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class MessageListService(@Autowired val kgMessageListDao: KgMessageListDao) {
    fun getMessageListBySchoolId(id: String): List<KgMessageList> {
        val message = kgMessageListDao.getMessage(id, 0)
        return message
    }

    fun getMessageListByClassroomId(id: String): Any {
        val message = kgMessageListDao.getMessage(id, 1)
        return message

    }
}