package kindergarten.security

import kindergarten.web.entity.KgUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUserFactory private constructor() {
    companion object {
        //创建jwtUser
        fun create(passport: KgUser): JwtUser {
            return with(passport) {
                JwtUser(id.toString(), tel!!, loginPassword!!, listOf(CustomGrantedAuthority(roleName!!)), token!!)
            }
        }

        //在拦截之后获取jwtUser
        fun getJwtUserAfterFilter(): JwtUser {
            val securityContext = SecurityContextHolder.getContext()
            return securityContext.authentication.principal as JwtUser
        }

    }
}
