package kindergarten.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

/**
 * Created by zhangruiyu on 2017/4/16.
 */
@Component
//@ConfigurationProperties(prefix = "spring.datasource")
class EnvironmentUtils {
    @Value("\${spring.datasource.url}")
    lateinit var url: String
    @Value("\${spring.datasource.username}")
    lateinit var username: String
    @Value("\${spring.datasource.password}")
    lateinit var password: String
    @Value("\${spring.datasource.driver-class-name}")
    lateinit var driver_class_name: String

}

