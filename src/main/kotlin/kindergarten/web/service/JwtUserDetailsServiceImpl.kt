package kindergarten.web.service

import kindergarten.web.dao.KgUserDao
import kindergarten.web.entity.JwtUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


/**
 * Created by zhangruiyu on 2017/5/10.
 */
@Service
open class JwtUserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private lateinit var mTPassportDao: KgUserDao

    //可以从缓存里取数据  不一定从数据库里取
    override fun loadUserByUsername(username: String): UserDetails {
        val queryUserByTel = mTPassportDao.queryUserAndRole(username) ?: throw  UsernameNotFoundException("手机号不存在")
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        queryUserByTel.roles = arrayOf(queryUserByTel.get("role_name") as String)
        return JwtUserFactory.create(queryUserByTel)
    }

}