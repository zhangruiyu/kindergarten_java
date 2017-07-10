package kindergarten.web.dao

import kindergarten.web.entity.KgMessageList
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Repository
interface KgMessageListDao : BaseMapper<KgMessageList> {
    fun getMessage(): List<KgMessageList>
}