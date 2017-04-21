package kindergarten

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import com.sun.tools.javac.tree.TreeInfo.args
import kindergarten.helper.DBHelper
import org.springframework.boot.Banner
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.beetl.sql.core.db.MySqlStyle
import org.beetl.sql.ext.spring4.BeetlSqlDataSource
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean
import org.springframework.context.annotation.Primary
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer
import org.apache.catalina.manager.StatusTransformer.setContentType
import org.beetl.core.resource.WebAppResourceLoader
import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.beetl.sql.core.*
import org.beetl.sql.ext.DebugInterceptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.http.converter.HttpMessageConverter
import java.io.IOException
import javax.sql.DataSource


@SpringBootApplication
//@MapperScan("com.kindergarten.dao")
//@EnableConfigurationProperties(Profile::class) //从application.properties添加属性的类
class KindergartenApplication : CommandLineRunner {
    //    @Autowired
//    private val mPersonDao: AuthDao? = null
    override fun run(vararg args: String?) {
//        System.out.println(this.mPersonDao!!.getById(1))
    }
    //用fastjson解析
    @Bean
    fun fastJsonHttpMessageConverters(): HttpMessageConverters {
        var fastConverter = FastJsonHttpMessageConverter()
        var fastJsonConfig = FastJsonConfig()
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat)
        fastConverter.fastJsonConfig = fastJsonConfig
        return HttpMessageConverters(fastConverter)
    }


    @Bean(initMethod = "init", name = arrayOf("beetlConfig"))
    fun getBeetlGroupUtilConfiguration(): BeetlGroupUtilConfiguration {

        val beetlGroupUtilConfiguration = BeetlGroupUtilConfiguration()
        val patternResolver = ResourcePatternUtils.getResourcePatternResolver(DefaultResourceLoader())
        try {
            val root = patternResolver.getResource("classpath:templates").file.toString()
            val webAppResourceLoader = WebAppResourceLoader(root)
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader)

            beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"))
            return beetlGroupUtilConfiguration
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    @Bean(name = arrayOf("beetlViewResolver"))
    fun getBeetlSpringViewResolver(@Qualifier("beetlConfig") beetlGroupUtilConfiguration: BeetlGroupUtilConfiguration): BeetlSpringViewResolver {
        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8")
        beetlSpringViewResolver.order = 0
        beetlSpringViewResolver.config = beetlGroupUtilConfiguration
        return beetlSpringViewResolver
    }



    @Bean(name = arrayOf("beetlSqlScannerConfigurer"))
    fun getBeetlSqlScannerConfigurer(): BeetlSqlScannerConfigurer {
        val conf = BeetlSqlScannerConfigurer()
        conf.setBasePackage("kindergarten.dao")
        conf.daoSuffix = "Dao"
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean")
        return conf
    }

    @Bean(name = arrayOf("sqlManagerFactoryBean"))
    @Primary
    fun getSqlManagerFactoryBean(@Qualifier("datasource") datasource: DataSource): SqlManagerFactoryBean {
        val factory = SqlManagerFactoryBean()

        val source = BeetlSqlDataSource()
        source.masterSource = datasource
        factory.setCs(source)
        factory.setDbStyle(MySqlStyle())
        factory.setInterceptors(arrayOf<Interceptor>(DebugInterceptor()))
        factory.setNc(DefaultNameConversion())
        factory.setSqlLoader(ClasspathLoader("sql"))
        return factory
    }


    @Bean(name = arrayOf("datasource"))
    @ConfigurationProperties(prefix = "spring.datasource")
    fun getDataSource(): DataSource {
        println("-------------------- primaryDataSource init ---------------------")
        return DBHelper.getInstance().db
    }

    @Bean(name = arrayOf("txManager"))
    fun getDataSourceTransactionManager(@Qualifier("datasource") datasource: DataSource): DataSourceTransactionManager {
        val dsm = DataSourceTransactionManager()
        dsm.dataSource = datasource
        return dsm
    }
}

fun main(args: Array<String>) {
    val app = SpringApplication(KindergartenApplication::class.java)
    app.setBannerMode(Banner.Mode.OFF)
    app.run(*args)
}
