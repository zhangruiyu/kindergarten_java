package kindergarten

import kindergarten.custom.PrivateBCryptPasswordEncoder
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by zhangruiyu on 2017/7/4.
 */

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class BcryptEncoderTest {
    @Autowired lateinit var privateBCryptPasswordEncoder: PrivateBCryptPasswordEncoder

    @Test
    fun getTablePojoSqlTemplate() {
        val s = "123123123"
        val encode = privateBCryptPasswordEncoder.encode(s)
        assert(privateBCryptPasswordEncoder.matches(s,encode))
    }
}