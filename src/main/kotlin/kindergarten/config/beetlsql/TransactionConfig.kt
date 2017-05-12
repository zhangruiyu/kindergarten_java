package kindergarten.config.beetlsql

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import javax.sql.DataSource

/**
 * Created by zhangruiyu on 2017/5/12.
 */
@Configuration
open class TransactionConfig {

    @Bean(name = arrayOf("txManager"))
    open fun getDataSourceTransactionManager(@Qualifier("datasource") datasource: DataSource): DataSourceTransactionManager {
        val dsm = DataSourceTransactionManager()
        dsm.dataSource = datasource
        return dsm
    }
}