package kindergarten;

import kindergarten.config.redis.RedisUtil;
import kindergarten.security.JwtUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangruiyu on 2017/7/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestRedis {

    //
    @Resource(name = "redisTemplate")
    private ValueOperations<String, JwtUser> op;

//    @Autowired
//    private RedisTemplate<String, User> redisTemplate;


    @Test
    public void testObj() throws Exception {
        JwtUser user = new JwtUser("aa@126.com", "aa", "aa123456", "", "123");
//        new RedisUtil().set("user",user);
//        BoundValueOperations<String, User> op = redisTemplate.boundValueOps("person");
        op.set("aaaaa", user);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
//        User user1 = new RedisUtil().get('');
        JwtUser user1 = op.get("aaaaa");
        Assert.assertEquals("aa", op.get("aaaaa").getTel());
    }


}