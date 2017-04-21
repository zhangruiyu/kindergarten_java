package kindergarten.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.function.Function
import java.util.stream.Collectors

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUserFactory private constructor() {
    companion object {
        fun JewUser(passport: User_Passport): JwtUser {
            return JwtUser(passport.passport_id.toString(), passport.tel!!, passport.login_password!!, mapToGrantedAuthorities(passport.roles))
        }

        fun mapToGrantedAuthorities(authorities: List<String>): List<GrantedAuthority> {
            return authorities.stream()
                    .map(::SimpleGrantedAuthority)
                    .collect(Collectors.toList())
        }
    }
}
