package kindergarten.comm.rest.entity

open class YSBasicEntity<T> {
    var code: Int = 0
    var msg: String = ""
    var data: T? = null
}