package kindergarten.web.service

import kindergarten.config.JwtTokenUtil
import kindergarten.custom.PrivateBCryptPasswordEncoder
import com.utils.kindergartens.otherwise
import kindergarten.ext.throwMessageException
import com.utils.kindergartens.yes
import kindergarten.web.dao.KgUserDao
import kindergarten.web.entity.JwtUserFactory
import kindergarten.web.entity.KgUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AuthService(@Autowired val passportDao: KgUserDao, @Autowired val jwtTokenUtil: JwtTokenUtil) {
    @Transactional
    fun registerUser(tel: String, password: String, ipAddress: String): KgUser {
        //根据手机号查找
        val queryUserByTel = passportDao.queryUser(tel)
        if (queryUserByTel == null) {
            //添加用户
            passportDao.insertPassport(tel, PrivateBCryptPasswordEncoder().encode(password))
            //查询到passport_id
            val user_Passport = passportDao.queryUser(tel)!!
            //添加profile
            passportDao.insertProfile(user_Passport.id, tel, ipAddress)
            //添加权限
            passportDao.insertUserRole(user_Passport.id, "2")
            //不从数据库取了
            user_Passport.set("role_name", "USER")
            user_Passport.set("token", jwtTokenUtil.generateToken(JwtUserFactory.create(user_Passport)))

            return user_Passport
        } else {
            "该手机号已经注册".throwMessageException()
        }
    }

    fun login(tel: String, password: String): KgUser? {
        val queryUser = passportDao.queryUser(tel) ?: "该手机号没有注册".throwMessageException()
        PrivateBCryptPasswordEncoder().matches(password, queryUser.loginPassword).yes {
            val generateToken = jwtTokenUtil.generateToken(JwtUserFactory.create(queryUser))
            queryUser.set("token", generateToken)
        }.otherwise {
            "手机号或密码错误".throwMessageException()
        }

        return queryUser
    }
}
