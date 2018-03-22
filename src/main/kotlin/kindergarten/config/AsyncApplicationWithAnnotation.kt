package kindergarten.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.RejectedExecutionHandler
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.core.task.AsyncTaskExecutor
import java.util.concurrent.ThreadPoolExecutor


@Configuration
class AsyncApplicationWithAnnotation {
    private val log = LoggerFactory.getLogger(AsyncApplicationWithAnnotation::class.java)

    /**
     * 自定义异步线程池
     * @return
     */
    @Bean
    fun taskExecutor(): AsyncTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.setThreadNamePrefix("Anno-Executor")
        executor.maxPoolSize = 10
        executor.corePoolSize = 10
        // 设置拒绝策略
        executor.setRejectedExecutionHandler { r, executor -> }
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor
    }
}