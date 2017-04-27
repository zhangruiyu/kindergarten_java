package kindergarten.config.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.SecurityMetadataSource
import org.springframework.security.access.intercept.AbstractSecurityInterceptor
import org.springframework.security.access.intercept.InterceptorStatusToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.stereotype.Component

import javax.servlet.*
import java.io.IOException

/**
 * Created by zhangruiyu on 2017/4/24.
 */
@Component
class DemoSecurityInterceptor : AbstractSecurityInterceptor(), Filter {

    @Autowired
    private lateinit var securityMetadataSource: FilterInvocationSecurityMetadataSource

    @Autowired
    override fun setAccessDecisionManager(accessDecisionManager: AccessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager)
    }

    @Autowired
    override fun setAuthenticationManager(authenticationManager: AuthenticationManager) {
        super.setAuthenticationManager(authenticationManager)
    }


    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val fi = FilterInvocation(request, response, chain)
        invoke(fi)

    }


    override fun getSecureObjectClass(): Class<out Any> {
        return FilterInvocation::class.java
    }


    @Throws(IOException::class, ServletException::class)
    operator fun invoke(fi: FilterInvocation) {

        val token = super.beforeInvocation(fi)

        try {
            fi.chain.doFilter(fi.request, fi.response)
        } finally {
            super.afterInvocation(token, null)
        }

    }


    override fun obtainSecurityMetadataSource(): SecurityMetadataSource {
        return securityMetadataSource
    }


    override fun destroy() {

    }

    @Throws(ServletException::class)
    override fun init(filterconfig: FilterConfig) {

    }

}
