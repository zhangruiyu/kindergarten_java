package kindergarten.config.task

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * 线程池配置属性类
 * Created by Fant.J.
 */
@ConfigurationProperties(prefix = "task.pool")
class TaskThreadPoolConfig {
    var corePoolSize: Int = 0

    var maxPoolSize: Int = 0

    var keepAliveSeconds: Int = 0

    var queueCapacity: Int = 0
}
