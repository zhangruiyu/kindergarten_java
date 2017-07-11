package kindergarten.web.dao

import kindergarten.web.entity.KgDynamic
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/11.
 */
@Repository
interface KgDynamicDao : BaseMapper<KgDynamic> {
    @SqlStatement(params = "selectId,dynamic_type")
    fun selectDynamic(selectId: Int?, dynamic_type: Int): List<KgDynamic>?
}
