package kindergarten.entity

import com.alibaba.fastjson.annotation.JSONField
import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.sql.Date

/**
 * Created by zhangruiyu on 2017/4/21.
 */
class JwtUser(
        val id: String,
        private val tel: String,
        private val password: String,
        private val authorities: Collection<GrantedAuthority>) : UserDetails {

    //返回分配给用户的角色列表
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    @JSONField(serialize = false)
    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return tel
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
