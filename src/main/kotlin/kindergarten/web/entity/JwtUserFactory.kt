package kindergarten.web.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUserFactory private constructor() {
    companion object {
        fun create(passport: User_Passport): JwtUser {
            return create(passport.passport_id.toString(), passport.tel!!, passport.login_password!!
                    , passport.roles.map(::SimpleGrantedAuthority).toList(), passport.last_password_reset_date!!)
        }

        fun create(id: String, tel: String, password: String, authorities: Collection<GrantedAuthority>, lastPasswordResetDate: java.util.Date): JwtUser {
            return JwtUser(id, tel, password, authorities, lastPasswordResetDate)
        }

    }
}
