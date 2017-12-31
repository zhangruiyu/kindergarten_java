package kindergarten.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kindergarten.ext.*
import kindergarten.web.service.JwtUserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import java.io.Serializable
import java.util.Date
import java.util.HashMap

import kindergarten.custom.MessageException.Companion.TRY_AGAIN_LOGIN_CODE

/**
 * Created by zhangruiyu on 2017/5/15.
 */
@Component
class JwtTokenUtil : Serializable {

    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Autowired
    private val userDetailsService: JwtUserDetailsServiceImpl? = null

    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    fun getUsernameFromToken(token: String?): String? {
        if (token == null) {
            return ""
        }
        var username: String?
        try {
            val claims = getClaimsFromToken(token)
            username = claims!!.subject
        } catch (e: Exception) {
            username = null
        }

        return username
    }

    fun getUsernameFromHttpServletRequest(httpServletRequest: HttpServletRequest): String? {
        return getUsernameFromToken(httpServletRequest.getHeader(tokenHeader))
    }

    private fun getCreatedDateFromToken(token: String): Date? {
        var created: Date?
        try {
            val claims = getClaimsFromToken(token)
            created = Date(claims!![CLAIM_KEY_CREATED] as Long)
        } catch (e: Exception) {
            created = null
        }

        return created
    }

    private fun getExpirationDateFromToken(token: String): Date? {
        var expiration: Date?
        try {
            val claims = getClaimsFromToken(token)
            expiration = claims!!.expiration
        } catch (e: Exception) {
            expiration = null
        }

        return expiration
    }

    private fun getClaimsFromToken(token: String): Claims? {
        var claims: Claims?
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .body
        } catch (e: Exception) {
            claims = null
        }

        return claims
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + expiration!! * 1000)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration!!.before(Date())
    }

    //用用用户名和创建时间创建token
    fun generateToken(tel: String): String {
        val claims = HashMap<String, Any>()
        claims.put(CLAIM_KEY_USERNAME, tel)
        claims.put(CLAIM_KEY_CREATED, Date())
        return generateToken(claims)
    }

    internal fun generateToken(claims: Map<String, Any>): String {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    fun canTokenBeRefreshed(token: String, lastPasswordReset: Date): Boolean? {
        return !isTokenExpired(token)
    }

    fun refreshToken(token: String): String? {
        var refreshedToken: String?
        try {
            val claims = getClaimsFromToken(token)
            claims!!.put(CLAIM_KEY_CREATED, Date())
            refreshedToken = generateToken(claims)
        } catch (e: Exception) {
            refreshedToken = null
        }

        return refreshedToken
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean? {
        val user = userDetails as JwtUser
        val username = getUsernameFromToken(token)
        val created = getCreatedDateFromToken(token)
        //final Date expiration = getExpirationDateFromToken(token);
        return username == user.username && !isTokenExpired(token)
    }

    //直接根据request 里的 token获取token  适合在不需要登录的方法里获取登陆信息
    fun getJwtUser(httpServletRequest: HttpServletRequest): JwtUser? {
        val authToken = httpServletRequest.getHeader(tokenHeader)
        val userDetails = this.userDetailsService!!.loadUserByUsername(getUsernameFromToken(authToken)!!) as JwtUser?
        if (userDetails != null) {
            if (authToken == userDetails.token) {
                return userDetails
            } else {
                "认证已失效,请重新登录!".throwMessageException(TRY_AGAIN_LOGIN_CODE)
            }
        }
        return null
    }

    companion object {
        private const val serialVersionUID = -3301605591108950415L

        private val CLAIM_KEY_USERNAME = "sub"
        private val CLAIM_KEY_CREATED = "created"
    }


}
