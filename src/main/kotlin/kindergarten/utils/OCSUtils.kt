package kindergarten.utils

import kindergarten.comm.vals.CustomConstants

class OCSUtils {
    companion object {
        //去除仓库前缀
        fun toLocation(path: String): String {
            if (path.isEmpty()) {
                return@toLocation ""
            }
            return path.substringAfter("/1253631018/kindergartens")
        }

        //访问图片的url
        fun getPicUrl(path: String?): String {
            when {
                path == null || path.isEmpty() -> return@getPicUrl ""
                path.startsWith("http") -> return@getPicUrl path
                else -> {
                    return if (path.endsWith(".mp4")) {
                        CustomConstants.COSURL.picUrl + path
                    } else {
                        CustomConstants.COSURL.picUrl + path + "?imageView2/q/50"
                    }
                }

            }
        }
    }
}