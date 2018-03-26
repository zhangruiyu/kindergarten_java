package kindergarten.config.task

import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.lang.reflect.Method
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

@Configuration
class NativeAsyncTaskExecutePool : AsyncConfigurer {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    //注入配置类
    @Autowired
    internal var config: TaskThreadPoolConfig? = null

    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        //核心线程池大小
        executor.corePoolSize = config!!.corePoolSize
        //最大线程数
        executor.maxPoolSize = config!!.maxPoolSize
        //队列容量
        executor.setQueueCapacity(config!!.queueCapacity)
        //活跃时间
        executor.keepAliveSeconds = config!!.keepAliveSeconds
        //线程名字前缀
        executor.setThreadNamePrefix("KG-Executor")

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        executor.initialize()
        return executor
    }


    /**
     * 异步任务中异常处理
     * @return
     */
    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler {
        return AsyncUncaughtExceptionHandler { arg0, arg1, arg2 ->
            logger.error("==========================" + arg0.message + "=======================", arg0)
            logger.error("exception method:" + arg1.name)
        }
    }

}
