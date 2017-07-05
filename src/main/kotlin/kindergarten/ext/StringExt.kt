package kindergarten.ext

import kindergarten.custom.MessageException

/**
 * Created by zhangruiyu on 2017/6/20.
 */

fun String.throwMessageException(code: Int = MessageException.NORMAL_ERROR_CODE): Nothing {
    throw MessageException(this, code)
}