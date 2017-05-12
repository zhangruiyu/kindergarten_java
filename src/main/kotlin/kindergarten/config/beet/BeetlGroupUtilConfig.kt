package kindergarten.config.beet

import org.beetl.core.resource.WebAppResourceLoader
import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import java.io.IOException

/**
 * Created by zhangruiyu on 2017/5/12.
 */
@Configuration
open class BeetlGroupUtilConfig {
    @Bean(initMethod = "init", name = arrayOf("beetlConfig"))
    open fun getBeetlGroupUtilConfiguration(): BeetlGroupUtilConfiguration {

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
}