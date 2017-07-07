package kindergarten;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhangruiyu on 2017/7/7.
 */
public class JTT implements UserDetails {

    public String id;
    public String tel;
    public String encryptPassword;
    public List<GrantedAuthority> authorities;
    public String token;

    public JTT() {
    }

    public JTT(String id, String tel, String encryptPassword, List<GrantedAuthority> authorities, String token) {
        this.id = id;
        this.tel = tel;
        this.encryptPassword = encryptPassword;
        this.authorities = authorities;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
