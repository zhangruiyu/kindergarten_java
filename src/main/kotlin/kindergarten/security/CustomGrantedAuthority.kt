package kindergarten.security

import kindergarten.annotation.PoKo
import org.springframework.security.core.GrantedAuthority
import org.springframework.util.Assert

/**
 * Created by zhangruiyu on 2017/7/7.
 */
@PoKo class CustomGrantedAuthority : GrantedAuthority {

    var role: String = ""

    constructor(role: String) {
        Assert.hasText(role, "A granted authority textual representation is required")
        this.role = role
    }

    override fun getAuthority(): String {
        return role
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other is CustomGrantedAuthority) {
            return role.trim() == other.role.trim()
        }

        return false
    }

    override fun hashCode(): Int {
        return this.role.hashCode()
    }

    override fun toString(): String {
        return this.role
    }
}