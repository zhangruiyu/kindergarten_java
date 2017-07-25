package kindergarten.config.restTemplate

import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.RestOperations
import java.nio.charset.Charset


/**
 * Created by zhangruiyu on 2017/7/18.
 */
@Configuration
class RestTemplateConfig {
    @Bean
    @ConditionalOnMissingBean(RestOperations::class, RestTemplate::class)
    fun restTemplate(factory: ClientHttpRequestFactory): RestTemplate {
        // return new RestTemplate(factory);

        val restTemplate = RestTemplate(factory)

        // 使用 utf-8 编码集的 conver 替换默认的 conver（默认的 string conver 的编码集为"ISO-8859-1"）
        val messageConverters = restTemplate.messageConverters
        val iterator = messageConverters.iterator()
        while (iterator.hasNext()) {
            val converter = iterator.next()
            if (converter is StringHttpMessageConverter) {
                iterator.remove()
            }
        }
        messageConverters.add(StringHttpMessageConverter(Charset.forName("UTF-8")))

        return restTemplate
    }

    @Bean
    @ConditionalOnMissingBean(ClientHttpRequestFactory::class)
    fun simpleClientHttpRequestFactory(): ClientHttpRequestFactory {
        val factory = SimpleClientHttpRequestFactory()
        factory.setReadTimeout(15000)// ms
        factory.setConnectTimeout(15000)// ms
        return factory
    }
}