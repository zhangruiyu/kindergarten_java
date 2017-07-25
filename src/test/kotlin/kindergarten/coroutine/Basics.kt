package kindergarten.coroutine

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import kotlin.concurrent.thread

/**
 * Created by zhangruiyu on 2017/7/15.
 */
class Basics {
    @Test
    fun main() {
        launch(CommonPool) {
            delay(1000L)
            println("Wolrd")
            println(Thread.currentThread().name)
        }
        println("hello")
        println(Thread.currentThread().name)
        Thread.sleep(2000L)
    }

    @Test
    fun testRunblocking() = runBlocking {
        val job = launch(CommonPool) {
            delay(1000L)
            println("Wolrd")
            println(Thread.currentThread().name)
        }
        println("hello")
        job.join()
    }

    fun runb() = runBlocking {
        launch(CommonPool) {
            delay(1000L)
            println("Wolrd")
            println(Thread.currentThread().name)
        }
        println("hello")
        println(Thread.currentThread().name)
        Thread.sleep(2000L)
    }

    @Test
    fun testLight_Weight() = runBlocking {
        val s = List(100_0000) {
            launch(CommonPool) {
                delay(1000L)
                println(".")
                println(Thread.currentThread().name)
            }
        }
        s.forEach { it.join() }
    }

}