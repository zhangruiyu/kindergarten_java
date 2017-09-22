package kindergarten.ext

import kindergarten.custom.MessageException

/**
 * Created by zhangruiyu on 2017/6/20.
 */

fun String.throwMessageException(code: Int = MessageException.NORMAL_ERROR_CODE): Nothing {
    throw MessageException(this, code)
}

fun String.throwSuccess(code: Int = 200): Nothing {
    throw MessageException(this, code)
}

//重写toString保证怎么也不会toString为空
fun Any?.toString() = this?.toString() ?: "null"
