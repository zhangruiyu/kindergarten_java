package kindergarten.web.dao

import kindergarten.web.entity.KgMessageList
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Repository
interface KgMessageListDao : BaseMapper<KgMessageList> {
    @SqlStatement(params = "user_id,type")
    fun getMessage(user_id: String, type: Int): List<KgMessageList>

    @SqlStatement(params = "school_id,message,type")
    fun addMessage(schoold_id: String, message: String, type: Int): Any
}