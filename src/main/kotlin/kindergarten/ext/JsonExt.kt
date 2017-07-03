package kindergarten.ext

/**
 * Created by zhangruiyu on 2017/6/20.
 */

/**
 * 返回json给客户端
 */
//有返回值给客户端
fun Any?.jsonOk(code: Int = 200, msg: String = "") = ResponseData(code, msg, this)

//没返回值 只有提示
fun Any?.jsonOKNoData(msg: String = "", code: Int = 200) = ResponseData(code, msg, this)

//失败了
fun Any?.jsonNormalFail(code: Int = 1001, msg: String = "") = ResponseData(code, msg, this)

data  class ResponseData(var code: Int, var msg: String, var `data`: Any?)