package kindergarten.sql

import kindergarten.web.entity.KgDynamic
import org.beetl.sql.core.SQLManager
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by zhangruiyu on 2017/7/11.
 */

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class TestSqlMapping {
    @Autowired
    lateinit var sql: SQLManager
    @Test
    fun select() {
        val select = sql.select("kgDynamic.getMessage", KgDynamic::class.java)
        print(select)
        print(select)
    }
}