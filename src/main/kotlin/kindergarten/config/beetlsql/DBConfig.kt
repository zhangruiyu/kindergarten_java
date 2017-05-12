package kindergarten.config.beetlsql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import javax.sql.DataSource

/**
 * Created by zhangruiyu on 2017/5/12.
 */

@Configuration
open class DBConfig {

   /* DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
    druidDataSource.setUrl(environment.getProperty("spring.datasource.url"));
    druidDataSource.setUsername(environment.getProperty("spring.datasource.username"));
    druidDataSource.setPassword(environment.getProperty("spring.datasource.password"));
    druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
    druidDataSource.setInitialSize(5);
    druidDataSource.setMaxActive(10);
    return druidDataSource;*/
    @Bean(name = arrayOf("datasource"))
    open fun getDataSource(env: Environment): DataSource {
//        TaobaoClient
        println("-------------------- primaryDataSource init ---------------------")
        val db = DataSourceBuilder.create().url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username")).password(env.getProperty("spring.datasource.password"))
                .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
                .build()
        return db
    }
}
