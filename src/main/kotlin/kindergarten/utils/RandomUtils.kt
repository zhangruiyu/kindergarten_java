package kindergarten.utils

import java.util.*

/**
 * Created by zhangruiyu on 2017/6/27.
 */
class RandomUtils {
    companion object {
        fun getRandNum(charCount: Int = 6): String {
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

}