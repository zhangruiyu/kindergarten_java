package kindergarten.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by zhangruiyu on 2017/4/24.
 */
@Component
public class DemoAccessDecisionManager implements AccessDecisionManager {

    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes == null) {
            return;
        }

        for (ConfigAttribute ca : configAttributes) {

            String needRole = ca.getAttribute();

            //ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
            for (GrantedAuthority ga : authentication.getAuthorities()) {

                if (needRole.trim().equals(ga.getAuthority().trim())) {

                    return;
                }

            }

        }

        throw new AccessDeniedException("没有权限进行操作！");

    }

    public boolean supports(ConfigAttribute attribute) {

        return true;

    }

    public boolean supports(Class<?> clazz) {
        return true;

    }


}
