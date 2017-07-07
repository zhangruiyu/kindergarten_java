package kindergarten.security

import com.alibaba.fastjson.JSON
import com.utils.kindergartens.no
import com.utils.kindergartens.otherwise
import kindergarten.annotation.PoKo
import com.utils.kindergartens.yes
import kindergarten.custom.MessageException
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.throwMessageException
import kindergarten.web.service.JwtUserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.ValueOperations
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
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
class JwtAuthenticationTokenFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var userDetailsService: JwtUserDetailsServiceImpl

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Value("\${jwt.header}")
    private lateinit var tokenHeader: String

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain) {
        //不包含public才去查用户
        request.requestURI.contains("/public").no {
            val authHeader = request.getHeader(this.tokenHeader)
            if (authHeader == null) {
                val printWriter = response.writer
                printWriter.use {
                    printWriter.append(JSON.toJSONString("请登录后再试".jsonNormalFail(code = MessageException.TRY_LOGIN_CODE)))
                }
                return
            } else {
                val authToken = authHeader/*.substring(tokenHead.length) // The part after "Bearer "*/
                val username = jwtTokenUtil.getUsernameFromToken(authToken)

                logger.info("checking authentication " + username)

                if (username != null && SecurityContextHolder.getContext().authentication == null) {

                    val userDetails = this.userDetailsService.loadUserByUsername(username)
                    if (userDetails != null && userDetails is JwtUser) {

                        if (authToken == userDetails.token) {
                            setAuthentication(jwtTokenUtil, authToken, userDetails, request, username)
                        } else {
                            val printWriter = response.writer
                            printWriter.use { response.writer.append(JSON.toJSONString("认证已失效,请重新登录!".jsonNormalFail(code = MessageException.TRY_AGAIN_LOGIN_CODE))) }
                            return
                        }
                    }

                }else{
                    val printWriter = response.writer
                    printWriter.use { response.writer.append(JSON.toJSONString("认证已失效,请尝试重新登录!".jsonNormalFail(code = MessageException.TRY_AGAIN_LOGIN_CODE))) }
                    return
                }
            }

        }
        chain.doFilter(request, response)
    }

    private fun setAuthentication(jwtTokenUtil: JwtTokenUtil, authToken: String?, userDetails: UserDetails, request: HttpServletRequest, username: String?) {
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