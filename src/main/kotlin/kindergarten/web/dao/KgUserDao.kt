package kindergarten.web.dao

import kindergarten.web.entity.KgUser
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository


@Repository
interface KgUserDao : BaseMapper<KgUser> {


    @SqlStatement(params = "tel")
    fun queryUser(tel: String): KgUser?

    fun queryUser(user_Passport: KgUser): KgUser?

    @SqlStatement(params = "tel")
    fun queryUserAndRole(tel: String): KgUser?

    @SqlStatement(params = "tel,login_password")
    fun insertPassport(tel: String, login_password: String)

    @SqlStatement(params = "passport_id,role_id")
    fun insertUserRole(passport_id: Int?, role_id: String)

    @SqlStatement(params = "passport_id,tel,register_ip")
    fun insertProfile(passport_id: Int?, tel: String, register_ip: String)
}