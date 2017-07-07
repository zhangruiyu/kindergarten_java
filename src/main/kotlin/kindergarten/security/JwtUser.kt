package kindergarten.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIgnoreType
import kindergarten.annotation.PoKo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by zhangruiyu on 2017/4/21.
 */
@PoKo
open class JwtUser : UserDetails {

    open var id: String? = null
    open var tel: String? = null
    open var encryptPassword: String? = null
    open var userAuthorities:  List<GrantedAuthority> = emptyList()
    open var token: String? = null

    constructor(id: String?, tel: String?, encryptPassword: String?, authorities: List<CustomGrantedAuthority>, token: String?) {
        this.id = id
        this.tel = tel
        this.encryptPassword = encryptPassword
        this.userAuthorities = authorities
        this.token = token
    }

    //返回分配给用户的角色列表
    @JsonIgnore
    open override fun getAuthorities(): Collection<GrantedAuthority> {
        return userAuthorities
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    open override fun getPassword(): String {
        return encryptPassword!!
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    open override fun getUsername(): String {
        return tel!!
    }

    // 账户是否未过期
    @JsonIgnoreProperties(ignoreUnknown = true)
    open override fun isAccountNonExpired(): Boolean {
        return true
    }

    // 账户是否未锁定
    open override fun isAccountNonLocked(): Boolean {
        return true
    }

    // 密码是否未过期
    @JsonIgnoreProperties(ignoreUnknown = true)
    open override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    // 账户是否激活
    @JsonIgnoreProperties(ignoreUnknown = true)
    open override fun isEnabled(): Boolean {
        return true
    }


}
