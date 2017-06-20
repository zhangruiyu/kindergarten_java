package kindergarten.config.other

import com.alibaba.fastjson.serializer.AfterFilter
import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import org.springframework.boot.autoconfigure.web.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by zhangruiyu on 2017/5/11.
 */
@Configuration
open class FastJsonConfig {

    //用fastjson解析
    @Bean
    open fun fastJsonHttpMessageConverters(): HttpMessageConverters {
        val fastConverter = FastJsonHttpMessageConverter()
        val fastJsonConfig = FastJsonConfig()
//        fastJsonConfig.setSerializeFilters(CustomBeforeFilter())
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat)
        fastConverter.fastJsonConfig = fastJsonConfig
        return HttpMessageConverters(fastConverter)
    }

}