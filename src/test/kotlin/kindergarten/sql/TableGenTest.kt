/*
package kindergarten.sql

import org.beetl.sql.core.SQLManager
import org.beetl.sql.ext.gen.GenConfig
import org.beetl.sql.ext.gen.GenFilter
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

*/
/**
 * Created by zhangruiyu on 2017/4/17.
 *//*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class TableGenTest {
    val genTabNames = arrayOf(
            "kg_user",
            "kg_profile",
            "kg_role",
            "kg_role_user",
            "kg_message_list"
            )
    @Autowired
    lateinit var sqlManager: SQLManager

    @Test
    fun getTablePojoSqlTemplate() {
//        sqlManager.genPojoCodeToConsole(name)
//        sqlManager.genALL("main.kotlin.kindergarten.web.entity", GenConfig(), MyGenFilter())
    }
    @Test
    fun printPojiSqlTemplate(){
        sqlManager.genPojoCodeToConsole("kg_eat")
        sqlManager.genSQLTemplateToConsole("kg_eat")
    }

    inner class MyGenFilter : GenFilter {
        override fun accept(tableName: String?): Boolean = !genTabNames.contains(tableName)
    }
}*/
