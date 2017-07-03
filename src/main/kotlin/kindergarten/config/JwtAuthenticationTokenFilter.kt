package kindergarten.config

import kindergarten.annotation.PoKo
import com.utils.kindergartens.yes
import kindergarten.ext.throwMessageException
import kindergarten.web.service.JwtUserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by zhangruiyu on 2017/5/14.
 */
@Component
@PoKo
class JwtAuthenticationTokenFilter : OncePerRequestFilter() {
    @Autowired
    private val userDetailsService: JwtUserDetailsServiceImpl? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Value("\${jwt.tokenHead}")
    private val tokenHead: String? = null

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain) {
        request.requestURI.contains("permission").yes {
            val authHeader = request.getHeader(this.tokenHeader)
            authHeader.let {
                val authToken = authHeader/*.substring(tokenHead.length) // The part after "Bearer "*/
                val username = jwtTokenUtil!!.getUsernameFromToken(authToken)

                logger.info("checking authentication " + username)

                if (username != null && SecurityContextHolder.getContext().authentication == null) {

                    val userDetails = this.userDetailsService!!.loadUserByUsername(username)

                    if (jwtTokenUtil.validateToken(authToken, userDetails)!!) {
                        val authentication = UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.authorities)
                        authentication.details = WebAuthenticationDetailsSource().buildDetails(
                                request)
                        logger.info("authenticated user $username, setting security context")
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                }
            }

        }
        chain.doFilter(request, response)
    }
}