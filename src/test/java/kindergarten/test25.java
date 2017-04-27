package kindergarten;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * Created by zhangruiyu on 2017/4/26.
 */

public class test25 {
    @Test
    public void test(){
        ClassLoader classLoader = test25.class.getClassLoader();
        URL resource = classLoader.getResource("application.properties");
        System.out.println(resource.getPath());
    }
}
