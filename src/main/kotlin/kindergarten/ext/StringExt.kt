package kindergarten.ext

import kindergarten.custom.MessageException

/**
 * Created by zhangruiyu on 2017/6/20.
 */

fun String.throwMessageException():Nothing {
    throw MessageException(this)
}