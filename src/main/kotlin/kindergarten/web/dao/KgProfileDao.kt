package kindergarten.web.dao

import kindergarten.web.entity.DynamicProfile
import kindergarten.web.entity.KgProfile
import kindergarten.web.entity.KgUser
import org.beetl.sql.core.annotatoin.Sql
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository
import java.util.ArrayList

/**
 * Created by zhangruiyu on 2017/7/11.
 */
@Repository
interface KgProfileDao : BaseMapper<KgProfile> {
    @SqlStatement(params = "user_id")
    fun selectProfile(user_id: String?): KgProfile

    @SqlStatement(params = "passport_id,tel,register_ip")
    fun insertProfile(passport_id: String?, tel: String, register_ip: String)

    @Sql("SELECT user_id,nick_name FROM kg_profile WHERE classroom_id = ?")
    fun selectAllClassUserInfo(classroomId: String): ArrayList<DynamicProfile>

    @SqlStatement(params = "platform,uid")
    fun getKgProfileByQQORWeiXin(platform: String, uid: String): KgUser?
}