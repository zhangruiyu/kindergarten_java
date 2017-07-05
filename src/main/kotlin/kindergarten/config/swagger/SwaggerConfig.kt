package kindergarten.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


/**
 * Created by zhangruiyu on 2017/5/5.
 */
@EnableSwagger2
@Configuration
class SwaggerConfig {
    @Bean
    open fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("kindergarten.web.controller"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("张瑞宇的RESTful APIs")
                .description("幼儿园app的api接口文档 www.baidu.com ")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact(Contact("张瑞宇", "www.baidu.com", "157418979@qq.com"))
                .version("1.0")
                .build()
    }
}
