package kindergarten.custom

/**
 * Created by zhangruiyu on 2017/5/24.
 */
class MessageException : RuntimeException {
    var code = NORMAL_ERROR_CODE

    constructor() {}

    constructor(message: String, code: Int = NORMAL_ERROR_CODE) : super(message) {
        this.code = code
    }

    //    constructor(message: String, cause: Throwable) : super(message, cause) {}
//
//    constructor(cause: Throwable) : super(cause) {}
//
//    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace) {}
    companion object {
        const val NORMAL_ERROR_CODE = 1001  // 普通只需要提示的错误code
        const val TRY_LOGIN_CODE = 1002 //没有登录
        const val TRY_AGAIN_LOGIN_CODE = 1003 //token'过期
        const val TRY_QQ_WECHAT_LOGIN_NOT_AUTH = 1004 //qq或者微信没有绑定账号
    }
}
