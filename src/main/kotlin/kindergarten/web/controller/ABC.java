package kindergarten.web.controller;

import kindergarten.security.JwtUser;
import kindergarten.security.JwtUserFactory;
import kindergarten.web.entity.KgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhangruiyu on 2017/7/4.
 */
@RestController
@RequestMapping(value = "/aaa")
public class ABC {
    @Autowired
    RedisTemplate stringRedisTemplate;
    @PostMapping(value = "/login")
    public void logissn() {
        ValueOperations<String, JwtUser> stringStringValueOperations = stringRedisTemplate.opsForValue();
        KgUser kgUser = new KgUser();
        kgUser.setToken("fgdagdagdsa");
        kgUser.setWxOpenId("fgdagdagdsa");
        JwtUser jwtUser = new JwtUser("1", "15241", "fgdagdagdsa", "fgdagdagdsa", new ArrayList<>(), new Date());
        stringStringValueOperations.set("tl",jwtUser);
    }
}
