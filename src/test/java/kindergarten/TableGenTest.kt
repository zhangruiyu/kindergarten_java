package kindergarten

import kindergarten.helper.DBHelper
import org.beetl.sql.core.ClasspathLoader
import org.beetl.sql.core.DefaultNameConversion
import org.beetl.sql.core.Interceptor
import org.beetl.sql.core.UnderlinedNameConversion
import org.beetl.sql.core.db.MySqlStyle
import org.beetl.sql.ext.DebugInterceptor
import org.beetl.sql.ext.spring4.BeetlSqlDataSource
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by zhangruiyu on 2017/4/17.
 */

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class TableGenTest {
    @Test
    fun getTablePojoSqlTemplate() {
        val factory = SqlManagerFactoryBean()
        val source = BeetlSqlDataSource()
        source.masterSource = DBHelper.getInstance().db
        factory.setCs(source)
        factory.setDbStyle(MySqlStyle())
        factory.setInterceptors(arrayOf<Interceptor>(DebugInterceptor()))
        factory.setNc(DefaultNameConversion())
        factory.setSqlLoader(ClasspathLoader("sql"))

        val sqlManager = factory.`object`
        sqlManager.genPojoCodeToConsole("User_Passport")
        sqlManager.genSQLTemplateToConsole("User_Passport")
    }
}