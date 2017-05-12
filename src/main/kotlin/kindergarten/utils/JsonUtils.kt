package kindergarten.utils

/**
 * Created by zhangruiyu on 2017/5/12.
 */
class JsonUtils {
    companion object {
        /**
         * 返回json给客户端
         */
        fun json(data: Any?, code: Int = 200, msg: String = ""): Any {
            return "{'code': $code, 'msg': $msg,'data':$data}"
        }
    }
}
