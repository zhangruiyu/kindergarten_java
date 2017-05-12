package kindergarten.web.service

import kindergarten.annotation.PoKo
import kindergarten.web.dao.TPassportDao
import kindergarten.web.entity.User_Passport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@PoKo class PassportService {

    @Autowired
    lateinit var passportDao: TPassportDao


    @Transactional
    fun regiseterUser(tel: String, password: String, ipAddr: String): User_Passport {
        //根据手机号查找
        val queryUserByTel = passportDao.queryUser(tel)
        //添加用户
        passportDao.insertPassport(tel, password, ipAddr)
        print(passportDao.queryUser(tel)!!)
        return passportDao.queryUser(tel)!!
    }

    fun login(tel: String, password: String): User_Passport? {
        val user_Passport = User_Passport()
        user_Passport.tel = tel
        user_Passport.login_password = password
        return passportDao.queryUser(user_Passport)
    }
}
