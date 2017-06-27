package kindergarten

import kindergarten.custom.CustomConstants
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.lang.Exception


@SpringBootApplication
class KindergartenApplication : CommandLineRunner {
    //    @Autowired
//    private val mPersonDao: AuthDao? = null
    override fun run(vararg args: String?) {
//        System.out.println(this.mPersonDao!!.getById(1))
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