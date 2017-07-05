package kindergarten.web.service

import kindergarten.web.dao.KgMessageListDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class MessageListService(@Autowired val kgMessageListDao: KgMessageListDao) {
    fun getMessageListBySchoolId() {

    }
}