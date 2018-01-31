package kindergarten.comm.vals


/**
 * Created by zhangruiyu on 2017/5/16.
 */
object CustomConstants {
    object CustomPermission {
        const val Role_USER = "USER"
        const val Role_TEACHER = "TEACHER"
        const val Role_ADMIN = "ADMIN"

        const val Role_USER_CODE = "2"
        const val Role_TEACHER_CODE = "3"
        const val Role_ADMIN_CODE = "1"

        //接口如果有token那么就查询  不会做踢下线操作
        const val CAN_USE_TOKEN_URL = "/canUserToken"
        const val USER = "hasAnyRole('$Role_USER','$Role_TEACHER','$Role_ADMIN')"
        const val USER_URL = "/user/normal"
        const val TEACHER = "hasAnyRole('$Role_TEACHER','$Role_ADMIN')"
        const val TEACHER_URL = "/user/teacher"

        val USER_Role = arrayOf("USER", "ADMIN")
        const val ADMIN = "hasRole('ADMIN')"
        //        const val ONLY_USER = "hasRole('USER')"
        const val USER_ADMIN = "hasRole('USER') AND hasRole('ADMIN')"

        fun getRoleCode(roleName: String): String {
            return when (roleName.substringAfter("ROLE_")) {
                Role_USER -> Role_USER_CODE
                Role_TEACHER -> Role_TEACHER_CODE
                Role_ADMIN -> Role_ADMIN_CODE
                else -> Role_USER_CODE
            }
        }
    }

    object ShoppingPoint {
        const val NothingPoint = 0
        const val FirstStart = 5
    }

    object PicType {
        //图片类型
        //0是发表动态 1是视频动态 2是饮食图片
        //多次签名
        val periodTypes = arrayOf("dynamic/pic_dynamic", "dynamic/video_dynamic", "eat/pic")
        val oneTypes = arrayOf("avatar")
    }

    object COSURL {
        //图片类型
        //0是发表动态
        val picUrl = "http://kindergartens-1253631018.pictj.myqcloud.com"

    }
}
