package kindergarten.config.beetlsql

import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by zhangruiyu on 2017/5/12.
 */
@Configuration
open class BeetlSqlScannerConfig {
    @Bean(name = arrayOf("beetlSqlScannerConfigurer"))
    open fun getBeetlSqlScannerConfigurer(): BeetlSqlScannerConfigurer {
        val conf = BeetlSqlScannerConfigurer()
        conf.setBasePackage("kindergarten.web.dao")
        conf.daoSuffix = "Dao"
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean")
        return conf
    }
}