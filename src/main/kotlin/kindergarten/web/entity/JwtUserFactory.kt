package kindergarten.web.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUserFactory private constructor() {
    companion object {
        fun create(passport: KgUser): JwtUser {
            return create(passport.id.toString(), passport.tel!!, passport.loginPassword!!
                    , passport.roles.map(::SimpleGrantedAuthority).toList())
        }

        fun create(id: String, tel: String, password: String, authorities: Collection<GrantedAuthority>, lastPasswordResetDate: java.util.Date = Date()): JwtUser {
            return JwtUser(id, tel, password, authorities, lastPasswordResetDate)
        }

    }
}
