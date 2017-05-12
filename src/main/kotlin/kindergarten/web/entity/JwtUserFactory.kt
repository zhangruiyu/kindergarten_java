package kindergarten.web.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUserFactory private constructor() {
    companion object {
        fun create(passport: User_Passport): JwtUser {
            return JwtUser(passport.passport_id.toString(), passport.tel!!, passport.login_password!!, mapToGrantedAuthorities(passport.roles))
        }

        fun create(id: String, tel: String, password: String, authorities: Collection<GrantedAuthority>): JwtUser {
            return JwtUser(id, tel, password, authorities)
        }

        fun mapToGrantedAuthorities(authorities: List<String>): List<GrantedAuthority> {
            return authorities.stream()
                    .map(::SimpleGrantedAuthority)
                    .collect(Collectors.toList())
        }
    }
}
