package kindergarten.security

import com.alibaba.fastjson.annotation.JSONField
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUser constructor() : UserDetails {
    var id: String? = null
    private var tel: String? = null
    private var password: String? = null
    private var authorities: Collection<GrantedAuthority>? = null
    var lastPasswordResetDate: Date? = null
    var token: String? = null

    constructor(id: String, tel: String, password: String, token: String, authorities: Collection<GrantedAuthority>, lastPasswordResetDate: Date) : this() {
        this.id = id
        this.tel = tel
        this.password = password
        this.token = token
        this.authorities = authorities
        this.lastPasswordResetDate = lastPasswordResetDate
    }


    //返回分配给用户的角色列表
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities!!
    }

    @JSONField(serialize = false)
    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return tel!!
    }

    // 账户是否未过期
    @JSONField(serialize = false)
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    // 账户是否未锁定
    @JSONField(serialize = false)
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    // 密码是否未过期
    @JSONField(serialize = false)
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    // 账户是否激活
    @JSONField(serialize = false)
    override fun isEnabled(): Boolean {
        return true
    }


}
