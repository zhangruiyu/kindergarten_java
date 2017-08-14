package kindergarten

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.test.context.junit4.SpringRunner
import javax.annotation.Resource
import org.springframework.data.redis.core.StringRedisTemplate



/**
 * Created by zhangruiyu on 2017/7/5.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class Redis {
    @Autowired
    private val stringRedisTemplate: StringRedisTemplate? = null

    @Autowired
    private val redisTemplate: RedisTemplate<String, User>? = null

//    @Autowired
//    private lateinit var ser: ValueOperations<String, Serializable>


//    @DeviceYsResource(name="redisTemplate")
//    lateinit var feedOp: ValueOperations<String, User>

    @Test
    @Throws(Exception::class)
    fun testObj() {
        val user = User("aa@126.com", "aa", "aa123456", "aa", "123")
        val opsForValue = redisTemplate!!.opsForValue()
        opsForValue.set("com.neox", user)
        //redisTemplate.delete("com.neo.f");
        val user1 = opsForValue.get("com.neox")
//        Assert.assertEquals("aa", ser.get("com.neox").id2)
        println(user1)
    }

}