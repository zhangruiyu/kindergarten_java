package kindergarten.web.service

import com.utils.kindergartens.otherwise
import com.utils.kindergartens.yes
import kindergarten.comm.method.MessageUitils
import kindergarten.comm.vals.CustomConstants
import kindergarten.custom.PrivateBCryptPasswordEncoder
import kindergarten.ext.*
import kindergarten.security.JwtTokenUtil
import kindergarten.security.JwtUser
import kindergarten.security.JwtUserFactory
import kindergarten.utils.OCSUtils
import kindergarten.utils.RandomUtils
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.dao.KgUserDao
import kindergarten.web.entity.KgProfile
import kindergarten.web.entity.custom.ProfileAlteredInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

@Service
class AuthService(
        @Autowired val passportDao: KgUserDao,
        @Autowired val kgProfileDao: KgProfileDao,
        @Autowired val jwtTokenUtil: JwtTokenUtil,
        @Autowired val stringRedisTemplate: StringRedisTemplate,
        @Autowired val privateBCryptPasswordEncoder: PrivateBCryptPasswordEncoder
) {
    companion object {
        const val authCodePrefix: String = "KINDERGARTEN_AUTH_CODE"
        const val authTokenPrefix: String = "KINDERGARTEN_AUTH_TOKEN"
    }

    @Suppress("SpringKotlinAutowiring")
    @Resource(name = "redisTemplate")
    lateinit var valueOperations: ValueOperations<String, JwtUser>

    //尝试发送验证码
    fun trySendAuthCode(tel: String, ipAddress: String): ResponseData {
        //根据手机号查找
        val queryUserByTel = passportDao.queryUser(tel)
        return if (queryUserByTel == null) {
            val authCode = RandomUtils.getRandNum()
            //写入redis  1小时过期
            stringRedisTemplate.opsForValue().set("$authCodePrefix:$tel", authCode, 1, TimeUnit.HOURS)
            MessageUitils.sendMessageCode(authCode, tel)
            jsonOKNoData("验证码发送成功")
        } else {
            "该手机号已经注册".jsonNormalFail()
        }
    }

    //写入用户到数据库
    @Transactional
    fun registerUser(tel: String, password: String, ipAddress: String): ResponseData? {
        //根据手机号查找
        val queryUserByTel = passportDao.queryUser(tel)
        if (queryUserByTel == null) {
            //添加用户
            passportDao.insertPassport(tel, privateBCryptPasswordEncoder.encode(password))
            //查询到passport_id
            val user_Passport = passportDao.queryUser(tel)!!
            //添加profile
            kgProfileDao.insertProfile(user_Passport.id, tel, ipAddress)
            //添加权限
            passportDao.insertUserRole(user_Passport.id, "2")
            //不从数据库取了
            user_Passport.set("role_name", "USER")
            user_Passport.set("token", jwtTokenUtil.generateToken(tel))
            return user_Passport.jsonOk()
        } else {
            return "该手机号已经注册".jsonNormalFail()
        }
    }

    fun login(tel: String, password: String): ResponseData? {
        val queryUser = passportDao.queryUserAndRole(tel) ?: return "该手机号没有注册".jsonNormalFail()
        privateBCryptPasswordEncoder.matches(password, queryUser.loginPassword).yes {
            val generateToken = jwtTokenUtil.generateToken(tel)
            queryUser.token = generateToken
            val jwtUser = JwtUserFactory.create(queryUser)
            valueOperations.set("$authTokenPrefix:$tel", jwtUser, 60, TimeUnit.DAYS)
        }.otherwise {
            return "手机号或密码错误".jsonNormalFail()
        }

        return queryUser.jsonOk()
    }

    fun getKgProfile(id: String): KgProfile {
        val kgProfile = kgProfileDao.single(id)
        return kgProfile
    }


    fun getProfile(id: String): ResponseData {
        return getKgProfile(id).jsonOk()
    }

    fun reviseProfile(id: String, checkGender: Int, relationCheck: Int, address: String, avatarUrl: String): ResponseData {
        passportDao.updateProfile(id, checkGender, relationCheck, address, avatarUrl)
        return ProfileAlteredInfo(checkGender, relationCheck, address, OCSUtils.getPicUrl(avatarUrl)).jsonOk()
    }
}
