package kindergarten.config.other

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat


/**
 * Created by zhangruiyu on 2017/5/11.
 */
@Configuration
class JackJsonConfig {

    @Bean
    fun getObjectMapper(): ObjectMapper = ObjectMapper().setDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))

}
