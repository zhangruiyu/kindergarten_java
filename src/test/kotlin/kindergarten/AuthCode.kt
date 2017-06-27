package kindergarten

import org.junit.Test
import java.util.*


/**
 * Created by zhangruiyu on 2017/6/27.
 */
class AuthCode {
    @Test
    fun contextLoads() {
        for (i in 0..100){
            println(getRandNum(6))
        }
    }

    fun getRandNum(charCount: Int): String {
        var charValue = ""
        for (i in 0..charCount - 1) {
            charValue += randomInt(0, 10).toString()
        }
        return charValue
    }

    fun randomInt(from: Int, to: Int): Int {
        val r = Random()
        return from + r.nextInt(to - from)
    }
}
