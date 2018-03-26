package kindergarten

import kindergarten.config.task.TaskThreadPoolConfig
import kindergarten.config.cos.OCSConfig
import org.beetl.core.resource.WebAppResourceLoader
import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.scheduling.annotation.EnableAsync
import java.io.IOException
import java.lang.Exception


@SpringBootApplication
@EnableConfigurationProperties(value = [OCSConfig::class, TaskThreadPoolConfig::class])
@EnableAsync
class KindergartenApplication : CommandLineRunner {
    //    @Autowired
//    private val mPersonDao: AuthDao? = null
    override fun run(vararg args: String?) {
//        System.out.println(this.mPersonDao!!.getById(1))
    }

    @Bean(initMethod = "init", name = ["beetlConfig"])
    fun getBeetlGroupUtilConfiguration(): BeetlGroupUtilConfiguration {

        val beetlGroupUtilConfiguration = BeetlGroupUtilConfiguration()
        val patternResolver = ResourcePatternUtils.getResourcePatternResolver(DefaultResourceLoader())
        try {
            val root = patternResolver.getResource("classpath:templates").file.toString()
            val webAppResourceLoader = WebAppResourceLoader(root)
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader)

            beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"))
            return beetlGroupUtilConfiguration
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    @Bean(name = ["beetlViewResolver"])
    fun getBeetlSpringViewResolver(@Qualifier("beetlConfig") beetlGroupUtilConfiguration: BeetlGroupUtilConfiguration): BeetlSpringViewResolver {
        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8")
        beetlSpringViewResolver.order = 0
        beetlSpringViewResolver.config = beetlGroupUtilConfiguration
        return beetlSpringViewResolver
    }
}

fun main(args: Array<String>) {
    val app = SpringApplication(KindergartenApplication::class.java)
    app.setBannerMode(Banner.Mode.CONSOLE)
    app.run(*args)
//    arrayOf("http://localhost:8080/user/login/1", "http://localhost:8080/index.html").openUrl()
}


/**
 * 打开测试的网页
 */
private fun Array<String>.openUrl() = this.forEach { it.openUrl() }

private fun String.openUrl() {
    //获取操作系统的名字
    val osName = System.getProperty("os.name")
    when {
        osName.startsWith("Mac OS") -> Class.forName("com.apple.eio.FileManager").getDeclaredMethod("openURL", *arrayOf<Class<*>>(String::class.java)).invoke(null, *arrayOf<Any>(this))
        osName.startsWith("Windows") -> Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler ${this}")
        else -> {
            var flag = true
            // Unix or Linux的打开方式
            arrayOf("firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape").forEach {
                if (Runtime.getRuntime().exec(arrayOf("which", it)).waitFor() == 0) {
                    flag = false
                    Runtime.getRuntime().exec(arrayOf(it, this))
                    return
                }
            }
            if (flag) throw Exception("Could not find web browser")
        }
    }
}