package kindergarten.web.service

import kindergarten.annotation.PoKo
import kindergarten.config.JwtTokenUtil
import kindergarten.utils.PrivateBCryptPasswordEncoder
import kindergarten.web.dao.TPassportDao
import kindergarten.web.entity.JwtUserFactory
import kindergarten.web.entity.User_Passport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.coroutines.experimental.buildSequence


@Service
@PoKo class AuthService {

    @Autowired
    lateinit var passportDao: TPassportDao
    @Autowired lateinit var jwtTokenUtil: JwtTokenUtil

    @Transactional
    fun registerUser(tel: String, password: String, ipAddress: String): User_Passport {
        //根据手机号查找
        val queryUserByTel = passportDao.queryUser(tel)
        if (queryUserByTel == null) {
            //添加用户
            passportDao.insertPassport(tel, PrivateBCryptPasswordEncoder().encode(password))
            //查询到passport_id
            val user_Passport = passportDao.queryUser(tel)!!
            //添加profile
            passportDao.insertProfile(user_Passport.passport_id, tel, ipAddress)
            //添加权限
            passportDao.insertUserRole(user_Passport.passport_id, "2")
            //不从数据库取了
            user_Passport.set("role_name", "USER")
            user_Passport.set("token", jwtTokenUtil.generateToken(JwtUserFactory.create(user_Passport)))

            return user_Passport
        } else {
            throw RuntimeException("该手机号已经注册")
        }
    }

    fun login(tel: String, password: String): User_Passport? {
        val queryUser = passportDao.queryUser(tel) ?: throw RuntimeException("该手机号没有注册")
        val matches = PrivateBCryptPasswordEncoder().matches(password, queryUser.login_password)
        if (matches) {
            val generateToken = jwtTokenUtil.generateToken(JwtUserFactory.create(queryUser))
            queryUser.set("token", generateToken)
        } else {
            throw RuntimeException("手机号或密码错误")
        }

        return queryUser
    }
}
