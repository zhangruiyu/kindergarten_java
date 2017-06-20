package kindergarten.ext

/**
 * Created by zhangruiyu on 2017/6/20.
 */
//重写toString保证怎么也不会toString为空
fun Any?.toString() = if (this == null) {
    "null"
} else {
    toString()
}
