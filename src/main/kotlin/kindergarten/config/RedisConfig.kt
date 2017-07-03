package kindergarten.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.core.RedisTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.connection.RedisConnectionFactory





/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Configuration
@EnableCaching
class RedisConfig {
    @Bean
    fun keyGenerator(): KeyGenerator {
        return KeyGenerator { o, method, objects ->
            val sb = StringBuilder()
            sb.append(o.javaClass.name)
            sb.append(method.name)
            for (`object` in objects) {
                sb.append(`object`.toString())
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
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, String> {
        val template = StringRedisTemplate(factory)
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)

        val om = ObjectMapper()
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        jackson2JsonRedisSerializer.setObjectMapper(om)

        template.valueSerializer = jackson2JsonRedisSerializer
        template.afterPropertiesSet()
        return template
    }


}