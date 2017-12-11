package kindergarten.web.dao

import kindergarten.web.entity.KgEat
import kindergarten.web.entity.KgMessageList
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Repository
interface KgEatListDao : BaseMapper<KgEat> {
    @SqlStatement(params = "school_id,formatDate")
    fun getEatList(school_id: String, formatDate: String): ArrayList<KgEat>
}