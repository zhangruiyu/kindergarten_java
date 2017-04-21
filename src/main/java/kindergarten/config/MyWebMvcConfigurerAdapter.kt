package kindergarten.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry


/**
 * Created by zhangruiyu on 2017/4/20.
 */
@Configuration
class MyWebMvcConfigurerAdapter : WebMvcConfigurerAdapter() {
    /**
     * 配置静态访问资源
     * @param registry
     */
    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
//        registry?.addResourceHandler("/my/**")?.addResourceLocations("classpath:/my/")
        super.addResourceHandlers(registry)
    }

    override fun addViewControllers(registry: ViewControllerRegistry?) {
//        registry.let { registry!!.addViewController("/toLogin"). }

        super.addViewControllers(registry)
    }

    override fun addInterceptors(registry: InterceptorRegistry?) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.let { registry!!.addInterceptor(MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin", "/login") }

        super.addInterceptors(registry)
    }
}