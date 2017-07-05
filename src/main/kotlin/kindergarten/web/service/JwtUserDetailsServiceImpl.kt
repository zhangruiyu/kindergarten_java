package kindergarten.web.service

import kindergarten.security.JwtUser
import kindergarten.web.dao.KgUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ValueOperations
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.io.Serializable


/**
 * Created by zhangruiyu on 2017/5/10.
 */
@Service
open class JwtUserDetailsServiceImpl : UserDetailsService {
    @Autowired
//    private lateinit var valueOperations: ValueOperations<String, Any>
    private lateinit var valueOperations: ValueOperations<String, Serializable>

    @Autowired lateinit var mTPassportDao: KgUserDao
    //可以从缓存里取数据  不一定从数据库里取
    override fun loadUserByUsername(tel: String): UserDetails? {
        val user = valueOperations.get("${AuthService.authTokenPrefix}:$tel")
        if (user != null)
            return user as JwtUser
        else return null
//        if (user == null) {
//            val queryUserByTel = mTPassportDao.queryUserAndRole(tel) ?: throw  UsernameNotFoundException("手机号不存在")
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//            queryUserByTel.roleName = queryUserByTel.get("role_name") as String  已经查了 所以不用搞了
//            return JwtUserFactory.create(queryUserByTel)
//        } else {
//            if (user is JwtUser)
//                return user
//            else return null
//        }
    }

}