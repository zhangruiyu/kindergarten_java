package kindergarten.ext

import java.net.InetAddress
import java.net.UnknownHostException
import javax.servlet.http.HttpServletRequest

/**
 * Created by zhangruiyu on 2017/6/20.
 */
fun HttpServletRequest.getIpAddr(): String {
    var ip = getHeader("x-forwarded-for")
    if (ip == null || ip.isEmpty() || "unknown".equals(ip, ignoreCase = true)) {
        ip = getHeader("Proxy-Client-IP")
    }
    if (ip == null || ip.isEmpty() || "unknown".equals(ip, ignoreCase = true)) {
        ip = getHeader("WL-Proxy-Client-IP")
    }
    if (ip == null || ip.isEmpty() || "unknown".equals(ip, ignoreCase = true)) {
        ip = remoteAddr
        if (ip == "127.0.0.1") {
            //根据网卡取本机配置的IP
            var inet: InetAddress? = null
            try {
                inet = InetAddress.getLocalHost()
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }

            ip = inet!!.hostAddress
        }
    }
    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
    if (ip != null && ip.length > 15) {
        if (ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","))
        }
    }
    return ip
}