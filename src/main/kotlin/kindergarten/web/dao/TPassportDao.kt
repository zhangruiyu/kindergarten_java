package kindergarten.web.dao

import kindergarten.web.entity.User_Passport
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

@Repository
interface TPassportDao : BaseMapper<User_Passport> {


    @SqlStatement(params = "tel")
    fun queryUser(tel: String): User_Passport?

    fun queryUser(user_Passport: User_Passport): User_Passport?

    @SqlStatement(params = "tel,login_password,register_ip")
    fun insertPassport(tel: String, login_password: String, register_ip: String)
}
