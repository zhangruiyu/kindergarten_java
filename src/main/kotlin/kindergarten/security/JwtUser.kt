package kindergarten.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kindergarten.annotation.PoKo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by zhangruiyu on 2017/4/21.
 */
@PoKo
class JwtUser : UserDetails {

    final var id: String? = null
    final var tel: String? = null
    final var encryptPassword: String? = null
    final var userAuthorities: List<GrantedAuthority> = emptyList()
    final var token: String? = null

    constructor(id: String?, tel: String?, encryptPassword: String?, authorities: List<CustomGrantedAuthority>, token: String?) {
        this.id = id
        this.tel = tel
        this.encryptPassword = encryptPassword
        this.userAuthorities = authorities
        this.token = token
    }

    constructor(id: String?, tel: String?, encryptPassword: String?, anchorite: CustomGrantedAuthority, token: String?) :
            this(id, tel, encryptPassword, listOf(anchorite), token)


    //返回分配给用户的角色列表
    @JsonIgnore
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return userAuthorities
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    override fun getPassword(): String {
        return encryptPassword!!
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    override fun getUsername(): String {
        return tel!!
    }

    // 账户是否未过期
    @JsonIgnoreProperties(ignoreUnknown = true)
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    // 账户是否未锁定
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    // 密码是否未过期
    @JsonIgnoreProperties(ignoreUnknown = true)
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    // 账户是否激活
    @JsonIgnoreProperties(ignoreUnknown = true)
    override fun isEnabled(): Boolean {
        return true
    }


}
