package kindergarten.web.service

import kindergarten.security.JwtUser
import org.springframework.data.redis.core.ValueOperations
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.annotation.Resource


/**
 * Created by zhangruiyu on 2017/5/10.
 */
@Service
open class JwtUserDetailsServiceImpl : UserDetailsService {
    @Suppress("SpringKotlinAutowiring")
    @Resource(name = "redisTemplate")
    lateinit var valueOperations: ValueOperations<String, JwtUser>
    //可以从缓存里取数据  不一定从数据库里取
    override fun loadUserByUsername(tel: String): UserDetails? {
        val user = valueOperations.get("${AuthService.authTokenPrefix}:$tel")
        if (user is JwtUser) {
//            user.userAuthorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
            return user
        }
        else return null
//        return null
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