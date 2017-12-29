package kindergarten.web.service

import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOKNoData
import kindergarten.web.dao.KgMessageListDao
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.entity.KgMessageList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class MessageListService(@Autowired val kgMessageListDao: KgMessageListDao,
                         @Autowired val authSaervice: AuthService) {
    fun getMessageListBySchoolId(id: String): List<KgMessageList> {
        val message = kgMessageListDao.getMessage(id, 0)
        return message
    }

    fun getMessageListByClassroomId(id: String): Any {
        val message = kgMessageListDao.getMessage(id, 1)
        return message

    }

    fun addClassRoomMessage(id: String, message: String, type: Int): Any {
        return if (authSaervice.getKgProfile(id).schoolId?.isNotEmpty() == true) {
            kgMessageListDao.addMessage(authSaervice.getKgProfile(id).schoolId!!, message, type)
            //TODO 应该推送到所在班级所有家长
            "消息发步成功".jsonOKNoData()
        } else {
            "加入后再试".jsonNormalFail()
        }

    }
}