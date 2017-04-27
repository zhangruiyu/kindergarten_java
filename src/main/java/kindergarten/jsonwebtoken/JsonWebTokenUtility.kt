package kindergarten.jsonwebtoken

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

/**
 * Created by zhangruiyu on 2017/4/24.
 */
class JsonWebTokenUtility {
    val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS512
    private val secretKey: Key

    init {

        // THIS IS NOT A SECURE PRACTICE!
        // For simplicity, we are storing a static key here.
        // Ideally, in a microservices environment, this key would kept on a
        // config server.
        val encodedKey = "L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg=="
        secretKey = deserializeKey(encodedKey)

        // secretKey = MacProvider.generateKey(signatureAlgorithm);
    }

    fun createJsonWebToken(authTokenDetailsDTO: AuthTokenDetailsDTO): String {
        val token = Jwts.builder().setSubject(authTokenDetailsDTO.userId).claim("tel", authTokenDetailsDTO.tel)
                .claim("roles", authTokenDetailsDTO.roleNames).setExpiration(authTokenDetailsDTO.expirationDate)
                .signWith(signatureAlgorithm, secretKey).compact()
        return token
    }

    private fun deserializeKey(encodedKey: String): Key {
        val decodedKey = Base64.getDecoder().decode(encodedKey)
        val key = SecretKeySpec(decodedKey, signatureAlgorithm.jcaName)
        return key
    }

    fun parseAndValidate(token: String): AuthTokenDetailsDTO {
        var authTokenDetailsDTO: AuthTokenDetailsDTO? = null
        try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
            val userId = claims.subject
            val tel = claims["tel"] as String
            val roleNames = claims["roles"] as List<String>
            val expirationDate = claims.expiration

            authTokenDetailsDTO = AuthTokenDetailsDTO()
            authTokenDetailsDTO.userId = userId
            authTokenDetailsDTO.tel = tel
            authTokenDetailsDTO.roleNames = roleNames
            authTokenDetailsDTO.expirationDate = expirationDate
        } catch (ex: JwtException) {
            println(ex)
        }

        return authTokenDetailsDTO!!
    }

    private fun serializeKey(key: Key): String {
        val encodedKey = Base64.getEncoder().encodeToString(key.encoded)
        return encodedKey
    }
}
