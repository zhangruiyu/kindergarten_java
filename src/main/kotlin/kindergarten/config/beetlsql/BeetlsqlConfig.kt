package kindergarten.config.beetlsql

import org.beetl.sql.core.ClasspathLoader
import org.beetl.sql.core.DefaultNameConversion
import org.beetl.sql.core.Interceptor
import org.beetl.sql.core.UnderlinedNameConversion
import org.beetl.sql.core.db.MySqlStyle
import org.beetl.sql.ext.DebugInterceptor
import org.beetl.sql.ext.spring4.BeetlSqlDataSource
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

/**
 * Created by zhangruiyu on 2017/5/12.
 */
@Configuration
open class BeetlsqlConfig {
    @Bean(name = arrayOf("sqlManagerFactoryBean"))
    @Primary
    open fun getSqlManagerFactoryBean(@Qualifier("datasource") datasource: DataSource): SqlManagerFactoryBean {
        val factory = SqlManagerFactoryBean()

        val source = BeetlSqlDataSource()
        source.masterSource = datasource
        factory.setCs(source)
        factory.setDbStyle(MySqlStyle())
        factory.setInterceptors(arrayOf<Interceptor>(DebugInterceptor()))
        factory.setNc(UnderlinedNameConversion())
        factory.setSqlLoader(ClasspathLoader("sql"))
        return factory
    }
}