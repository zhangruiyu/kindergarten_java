package kindergarten.web.service

import kindergarten.web.dao.TPassportDao
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
    private lateinit var mTPassportDao: TPassportDao


    override fun loadUserByUsername(username: String): UserDetails {
        val queryUserByTel = mTPassportDao.queryUser(username) ?: throw  UsernameNotFoundException("手机号不存在")
        return JwtUserFactory.create(queryUserByTel)
    }

}