package kindergarten.config.beetlsql

import com.alibaba.druid.pool.DruidDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import javax.sql.DataSource

/**
 * Created by zhangruiyu on 2017/5/12.
 */

@Configuration
open class DBConfig {

    @Bean(name = ["dataSource"])
    fun druidDataSource(env: Environment): DataSource {
        val druidDataSource = DruidDataSource()
        druidDataSource.driverClassName = env.getProperty("spring.datasource.driver-class-name")
        druidDataSource.url = env.getProperty("spring.datasource.url")
        druidDataSource.username = env.getProperty("spring.datasource.username")
        druidDataSource.password = env.getProperty("spring.datasource.password")
        druidDataSource.validationQuery = "SELECT 1 FROM DUAL" //用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
        druidDataSource.initialSize = 5
        druidDataSource.maxActive = 10
        return druidDataSource
    }

    @Bean(name = ["txManager"])
    fun getDataSourceTransactionManager(@Qualifier("dataSource") datasource: DataSource): DataSourceTransactionManager {
        val dsm = DataSourceTransactionManager()
        dsm.dataSource = datasource
        return dsm
    }
}
