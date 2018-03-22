package kindergarten.web.service

import kindergarten.comm.vals.CustomConstants.CustomPermission.Role_TEACHER_CODE
import kindergarten.comm.vals.CustomConstants.CustomPermission.Role_USER_CODE
import kindergarten.comm.vals.CustomConstants.CustomPermission.getRoleCode
import kindergarten.security.JwtUser
import kindergarten.utils.OCSUtils
import kindergarten.web.dao.KgProfileDao
import kindergarten.web.dao.KgUserDao
import kindergarten.web.entity.KgProfile
import kindergarten.web.entity.KgUser
import kindergarten.web.entity.custom.KgAny
import kindergarten.web.entity.custom.ProfileAlteredInfo
import kindergarten.web.service.PassportService.Companion.authTokenPrefix
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.ArrayList
import javax.annotation.Resource

//修改 获取用户信息的server
@Service
class ProfileService(
        @Autowired val passportDao: KgUserDao,
        @Autowired val kgProfileDao: KgProfileDao
) {

    @Suppress("SpringKotlinAutowiring")
    @Resource(name = "redisTemplate")
    lateinit var valueOperations: ValueOperations<String, JwtUser>


    fun getKgProfileAndRoleCode(jwtUser: JwtUser): KgProfile {
        val single = kgProfileDao.single(jwtUser.id)
        single.roleCode = getRoleCode(valueOperations.get("$authTokenPrefix:${jwtUser.tel}")!!.userAuthorities[0].authority)
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

    fun getUserByTel(tel: String): KgUser? {
        return passportDao.getUserByTel(tel)
    }

    fun unbindQQORWechat(id: String, platform: String): Any {
        passportDao.updateQQORWechatOpenid(id, platform)
        return "解绑成功"
    }

    @Transactional
    fun bindQQORWechat(jwt: JwtUser, platform: String, uid: String, nickName: String, iconurl: String): String {
        val id = jwt.id
        passportDao.updateQQORWechatOpenid(id, platform, uid)
        val kgProfile = getKgProfile(id)
        //如果之前没有头像 则用第三方的头像
        val avatarUrl = if (kgProfile.avatar.isNullOrEmpty()) {
            iconurl
        } else {
            ""
        }
        kgProfileDao.updateQQORWechat(id, platform, nickName, avatarUrl)
        return "绑定成功"
    }

    fun getTelsByClassroom(schoolId: String, classroomId: String): Any {
        val groupBy = kgProfileDao.getTelsByClassroom(schoolId, classroomId).map {
            it["avatar"] = OCSUtils.getPicUrl(it["avatar"].toString())
            it
        }.groupBy {
                    it.tails["roleId"].toString()
                }
        val kgAny = KgAny()
        kgAny.tails["teachers"] = groupBy[Role_TEACHER_CODE]
        kgAny.tails["students"] = groupBy[Role_USER_CODE]
        return kgAny
    }

}
