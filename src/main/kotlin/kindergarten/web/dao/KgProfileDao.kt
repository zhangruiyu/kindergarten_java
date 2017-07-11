package kindergarten.web.dao

import kindergarten.web.entity.KgProfile
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper

/**
 * Created by zhangruiyu on 2017/7/11.
 */
interface KgProfileDao : BaseMapper<KgProfile> {
    @SqlStatement(params = "user_id")
    fun selectProfile(user_id: String?): KgProfile

    @SqlStatement(params = "passport_id,tel,register_ip")
    fun insertProfile(passport_id: Int?, tel: String, register_ip: String)
}