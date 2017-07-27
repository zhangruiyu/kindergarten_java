package kindergarten.utils

class OCSUtils() {
    companion object {

        fun toLocation(path: String): String {
            return path.substringAfter("/1253631018/kindergartens")
        }
    }
}