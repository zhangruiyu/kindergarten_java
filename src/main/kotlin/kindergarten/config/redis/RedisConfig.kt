package kindergarten.config.redis

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * Created by zhangruiyu on 2017/7/6.
 */
@Configuration
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    /*  @Bean
      fun connectionFactory(): JedisConnectionFactory {
          return JedisConnectionFactory()
      }*/

    /**
     * 生成key的策略

     * @return
     */
    @Bean
    override fun keyGenerator(): KeyGenerator {
        return KeyGenerator { target, method, params ->
            val sb = StringBuilder()
            sb.append(target.javaClass.name)
            sb.append(method.name)
            for (obj in params) {
                sb.append(obj.toString())
            }
            sb.toString()
        }
    }

    // 要启用缓存支持，我们需要创建一个新的 CacheManager bean
    @Bean
    fun cacheManager(redisTemplate: RedisTemplate<*, *>): CacheManager {
        val manager = RedisCacheManager(redisTemplate)
        return manager
    }

    @Bean
    fun <T> redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, T> {
        val template = RedisTemplate<String, T>()
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        val om = ObjectMapper()
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        //忽略掉无法序列化的 比如方法
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        jackson2JsonRedisSerializer.setObjectMapper(om)

        template.connectionFactory = factory
//        template.stringSerializer = StringRedisSerializer()
//        template.defaultSerializer = GenericJackson2JsonRedisSerializer(om)
        template.keySerializer = StringRedisSerializer()
        template.defaultSerializer = jackson2JsonRedisSerializer
        template.hashKeySerializer = template.keySerializer
//        template.hashValueSerializer = template.valueSerializer
        template.isEnableDefaultSerializer = true
        template.afterPropertiesSet()
        return template
    }

//    @Resource(name = "redisTemplate") val valueOperations: ValueOperations<String, JwtUser>
}