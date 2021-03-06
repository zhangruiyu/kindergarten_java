package kindergarten.web.dao

import kindergarten.web.entity.KgUser
import org.beetl.sql.core.annotatoin.Sql
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository


@Repository
interface KgUserDao : BaseMapper<KgUser> {


    @SqlStatement(params = "tel")
    fun queryUser(tel: String): KgUser?


    @SqlStatement(params = "tel")
    fun queryUserAndRole(tel: String): KgUser?

    @SqlStatement(params = "id,tel,login_password")
    fun insertPassport(id: String, tel: String, login_password: String)

    @SqlStatement(params = "passport_id,role_id")
    fun insertUserRole(passport_id: String?, role_id: String)

    @SqlStatement(params = "id,checkGender,relationCheck,address,avatarUrl")
    fun updateProfile(id: String, checkGender: Int, relationCheck: Int, address: String, avatarUrl: String)

    @Sql(value = "UPDATE kg_user SET login_password = (?) WHERE id = (?)")
    fun changePassword(changePassword: String, id: String)

    @Sql(value = "SELECT * FROM kg_user WHERE tel = ?")
    fun getUserByTel(tel: String): KgUser?

    @SqlStatement(params = "id,platform,uid")
    fun updateQQORWechatOpenid(id: String, platform: String, uid: String = "")


}