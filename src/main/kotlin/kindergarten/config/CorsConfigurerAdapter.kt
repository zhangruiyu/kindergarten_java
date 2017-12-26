package kindergarten.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class CorsConfigurerAdapter : WebMvcConfigurerAdapter() {

    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!.addMapping("/**")
    }
}