package kindergarten.comm.rest

import kindergarten.comm.rest.entity.YSBasicEntity
import kindergarten.comm.rest.entity.YSRegisterEntity
import kindergarten.comm.rest.entity.YSToken
import kindergarten.ext.throwMessageException
import org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON
import org.springframework.context.annotation.Scope
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import org.springframework.util.DigestUtils
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@Component
@Scope(value = SCOPE_SINGLETON)
class RestApi(var restTemplate: RestTemplate) {


    @Suppress("SpringKotlinAutowiring")
    @Resource(name = "redisTemplate")
    lateinit var valueOperations: ValueOperations<String, String>

    //    val YSTOKEN: String = ""
    @Synchronized
    fun restYSAdminToken(): String {
//        if (YSTOKEN.isNotEmpty()) {
//            return@restYSAdminToken YSTOKEN
//        }
        val ysToken: String? = valueOperations.get("YSTOKEN")
        if (!ysToken.isNullOrEmpty()) {
            return@restYSAdminToken ysToken!!
        }
        val requestEntity = LinkedMultiValueMap<String, String>()
        requestEntity.add("appKey", "b109fdee59b14b19b48927f627814c58")
        requestEntity.add("appSecret", "fa7d8a8c75176be997d80f13590dfaa6")
        val data = restTemplate.postForEntity<YSToken>("https://open.ys7.com/api/lapp/token/get", requestEntity, YSToken::class.java).body.composeApi()
        valueOperations.set("YSTOKEN", data.accessToken, 2, TimeUnit.DAYS)
        return data.accessToken
    }

    fun registerYSAccount(accountName: String, password: String) {
        val requestEntity = LinkedMultiValueMap<String, String>()
        requestEntity.add("accessToken", restYSAdminToken())
        requestEntity.add("accountName", accountName)
        requestEntity.add("password", DigestUtils.md5DigestAsHex(password.toByteArray()).toLowerCase())
        restTemplate.postForEntity("https://open.ys7.com/api/lapp/ram/account/create", requestEntity, YSRegisterEntity::class.java).body.composeApi()
    }
}

fun <T> YSBasicEntity<T>.composeApi(): T {
    if (code == 200) {
        return data as T
    } else {
        msg.throwMessageException()
    }
}