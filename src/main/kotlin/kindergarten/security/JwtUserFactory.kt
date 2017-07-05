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
                JwtUser(id.toString(), tel!!, loginPassword!!, token!!, listOf(SimpleGrantedAuthority(roleName)), Date())
            }
        }

        //在拦截之后获取jwtUser
        fun getJwtUserAfterFilter(): JwtUser? {
            val securityContext = SecurityContextHolder.getContext()
            if (securityContext == null) {
                return null
            } else {
                if (securityContext.authentication != null && securityContext.authentication.principal is JwtUser) {
                    return securityContext.authentication.principal as JwtUser
                } else return null
            }
        }

    }
}
