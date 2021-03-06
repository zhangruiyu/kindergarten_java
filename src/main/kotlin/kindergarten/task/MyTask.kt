package kindergarten.task

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * Created by zhangruiyu on 2017/4/20.
 */
@Configuration
@EnableScheduling
class MyTask {
    /**
     * 希望每10秒钟执行一次
     * cron:定时任务表达式
     * 指定:秒,分钟,小时,日期,月份,星期,年(年是可选)
     * *:任意
     */

    //10秒
    @Scheduled(cron = "0/10 * * * * *")
    @Async
    fun task1() {
        println("TASK1()${Thread.currentThread().name}")
    }

    // 1分钟
    @Scheduled(cron = "0 0/1 * * * * ")
    @Async
    fun task2() {
        println("TASK2()")
    }
}