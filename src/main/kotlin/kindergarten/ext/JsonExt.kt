package kindergarten.ext

/**
 * Created by zhangruiyu on 2017/6/20.
 */

/**
 * 返回json给客户端
 */
fun Any?.jsonOk(code: Int = 200, msg: String = "") = "{'code': $code, 'msg': $msg,'data':$this}"

fun Any?.jsonNormalFail(code: Int = 1001, msg: String = "") = "{'code': $code, 'msg': $msg,'data':$this}"
