package kindergarten.config.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.io.Serializable


/**
 * Created by zhangruiyu on 2017/7/3.
 * http://www.jianshu.com/p/2fcaeda3f2c6 参考
 * http://www.jianshu.com/p/a78de210a947
 */
@Configuration
@EnableCaching
class RedisConfig {


    @Bean
    fun connectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    fun strOperations(redisTemplate: RedisTemplate<String, String>): ValueOperations<String, String> {
        return redisTemplate.opsForValue()
    }

    @Bean
    fun intRedisTemplate(connectionFactory: JedisConnectionFactory): RedisTemplate<String, Int> {
        val redisTemplate = RedisTemplate<String, Int>()
        redisTemplate.connectionFactory = connectionFactory
        return redisTemplate
    }

    @Bean
    fun intOperations(redisTemplate: RedisTemplate<String, Int>): ValueOperations<String, Int> {
        return redisTemplate.opsForValue()
    }

    @Bean
    fun jackson2JsonRedisSerializer(objectMapper: ObjectMapper): Jackson2JsonRedisSerializer<Any> {
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(
                Any::class.java)
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)
        return jackson2JsonRedisSerializer
    }

    @Bean
    fun objRedisTemplate(connectionFactory: JedisConnectionFactory,
                         jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<Any>): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = connectionFactory
        redisTemplate.defaultSerializer = jackson2JsonRedisSerializer
        val stringRedisSerializer = StringRedisSerializer()
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.hashKeySerializer = stringRedisSerializer
        return redisTemplate
    }
    @Bean
    fun serializableRedisTemplate(connectionFactory: JedisConnectionFactory,
                         jackson2JsonRedisSerializer: Jackson2JsonRedisSerializer<Any>): RedisTemplate<String, Serializable> {
        val redisTemplate = RedisTemplate<String, Serializable>()
        redisTemplate.connectionFactory = connectionFactory
        redisTemplate.defaultSerializer = jackson2JsonRedisSerializer
        redisTemplate.valueSerializer = jackson2JsonRedisSerializer
        val stringRedisSerializer = StringRedisSerializer()
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.hashKeySerializer = stringRedisSerializer
        return redisTemplate
    }

    @Bean
    fun objOperations(redisTemplate: RedisTemplate<String, Any>): ValueOperations<String, Any> {
        return redisTemplate.opsForValue()
    }
    @Bean
    fun serializableOperations(redisTemplate: RedisTemplate<String, Serializable>): ValueOperations<String, Serializable> {
        return redisTemplate.opsForValue()
    }

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
/*

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
*/


}