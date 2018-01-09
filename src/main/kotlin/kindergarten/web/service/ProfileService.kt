package kindergarten.web.service

import kindergarten.comm.vals.CustomConstants.CustomPermission.getRoleCode
import kindergarten.config.redis.RedisUtil
import kindergarten.custom.PrivateBCryptPasswordEncoder
import kindergarten.security.JwtTokenUtil
import kindergarten.security.JwtUser
import kindergarten.utils.OCSUtils
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.dao.KgUserDao
import kindergarten.web.entity.KgProfile
import kindergarten.web.entity.KgUser
import kindergarten.web.entity.custom.ProfileAlteredInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import javax.annotation.Resource

//修改 获取用户信息的server
@Service
class ProfileService(
        @Autowired val passportDao: KgUserDao,
        @Autowired val kgProfileDao: KgProfileDao,
        @Autowired val jwtTokenUtil: JwtTokenUtil,
        @Autowired val stringRedisTemplate: StringRedisTemplate,
        @Autowired val privateBCryptPasswordEncoder: PrivateBCryptPasswordEncoder,
        @Autowired private val redisUtil: RedisUtil
) {
    companion object {
        const val authCodePrefix: String = "KINDERGARTEN_AUTH_CODE"
        const val authTokenPrefix: String = "KINDERGARTEN_AUTH_TOKEN"
        const val pushTokenPrefix: String = "KINDERGARTEN_PUSH_TOKEN"
    }

    @Suppress("SpringKotlinAutowiring")
    @Resource(name = "redisTemplate")
    lateinit var valueOperations: ValueOperations<String, JwtUser>


    fun getKgProfileAndRoleCode(jwtUser: JwtUser): KgProfile {
        val single = kgProfileDao.single(jwtUser.id)
        single.roleCode = getRoleCode(valueOperations.get("$authTokenPrefix:${jwtUser.tel}").userAuthorities[0].authority)
        return single
    }

    fun getKgProfile(id: String): KgProfile = kgProfileDao.single(id)

    fun updateKgProfile(kgProfile: KgProfile) = kgProfileDao.updateById(kgProfile)


    fun reviseProfile(id: String, checkGender: Int, relationCheck: Int, address: String, avatarUrl: String): ProfileAlteredInfo {
        passportDao.updateProfile(id, checkGender, relationCheck, address, avatarUrl)
        return ProfileAlteredInfo(checkGender, relationCheck, address, OCSUtils.getPicUrl(avatarUrl))
    }

    fun getKgProfileByQQORWeiXin(uid: String, platform: String): KgUser? {
        return kgProfileDao.getKgProfileByQQORWeiXin(platform, uid)
    }

    fun getUserByTel(tel: String):KgUser? {
        return passportDao.getUserByTel(tel)
    }
}
