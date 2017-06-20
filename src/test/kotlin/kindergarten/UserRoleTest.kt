package kindergarten

import kindergarten.web.entity.Use_Role_Passport
import org.beetl.sql.core.SQLManager
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by zhangruiyu on 2017/5/7.
 */

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserRoleTest {

    @Autowired lateinit var sqlManagerFactoryBean: SQLManager
    @Test
    fun getTablePojoSqlTemplate() {
//        val use_Role_Passport = Use_Role_Passport()
//        use_Role_Passport.passport_id = 1
//        print(  sqlManagerFactoryBean.select("tRole.selectUserAndRoles", Use_Role_Passport::class.java,use_Role_Passport))
    }
}