package kindergarten.utils

import kindergarten.comm.vals.CustomConstants

class OCSUtils() {
    companion object {
        //去除仓库前缀
        fun toLocation(path: String): String {
            if (path.isEmpty()) {
                return@toLocation ""
            }
            return path.substringAfter("/1253631018/kindergartens")
        }

        //访问图片的url
        fun getPicUrl(path: String): String {
            if (path.isEmpty()) {
                return@getPicUrl ""
            }
            return CustomConstants.COSURL.picUrl + path
        }
    }
}