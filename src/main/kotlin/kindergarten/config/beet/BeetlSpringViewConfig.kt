package kindergarten.config.beet

import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by zhangruiyu on 2017/5/12.
 */
@Configuration
open class BeetlSpringViewConfig {
    @Bean(name = arrayOf("beetlViewResolver"))
    open fun getBeetlSpringViewResolver(@Qualifier("beetlConfig") beetlGroupUtilConfiguration: BeetlGroupUtilConfiguration): BeetlSpringViewResolver {
        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8")
        beetlSpringViewResolver.order = 0
        beetlSpringViewResolver.config = beetlGroupUtilConfiguration
        return beetlSpringViewResolver
    }
}