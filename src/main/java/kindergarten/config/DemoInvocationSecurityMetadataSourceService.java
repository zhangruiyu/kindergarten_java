package kindergarten.config;

import kindergarten.entity.User_Role;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by zhangruiyu on 2017/4/24.
 */
@Component
public class DemoInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    SQLManager sql;
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public DemoInvocationSecurityMetadataSourceService() {

    }

    private void loadResourceDefine() {

        /*
         * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
         * sparta
         */
        // 假数据
        List<User_Role> roles = sql.all(User_Role.class); //查询角色列表
        roles.forEach(user_role -> {
            System.out.println(user_role.getRole_name()+"user_role");
        });

        resourceMap = new HashMap<>();

        for (User_Role role : roles) {
            ConfigAttribute ca = new SecurityConfig(role.getRole_name());

            Map<String, Object> params = new HashMap<>();
            params.put("roleId", role.getRole_id());
            // 查询每个角色对于的权限,我这里假设直接查到了url
            List<String> resources = Collections.singletonList("/user/*");

            for (String url : resources) {

                /*
                 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                 * sparta
                 */
                if (resourceMap.containsKey(url)) {

                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<>();
                    atts.add(ca);
                    resourceMap.put(url, atts);
                }

            }

        }

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        return null;
    }

    // 根据URL，找到相关的权限配置。根据路径获取访问权限的集合接口
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {

        FilterInvocation filterInvocation = (FilterInvocation) object;
        for (String url : resourceMap.keySet()) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
            HttpServletRequest httpRequest = filterInvocation.getHttpRequest();

            if (requestMatcher.matches(httpRequest)) {
                return resourceMap.get(url);
            }
        }

        return null;

    }

    @Override
    public boolean supports(Class<?> arg0) {

        return true;
    }

}