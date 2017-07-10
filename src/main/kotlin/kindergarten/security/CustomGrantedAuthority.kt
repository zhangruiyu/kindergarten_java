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

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }

        if (obj is CustomGrantedAuthority) {
            return role.trim() == obj.role.trim()
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