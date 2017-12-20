package kindergarten.comm.vals


/**
 * Created by zhangruiyu on 2017/5/16.
 */
object CustomConstants {
    object CustomPermission {
        const val USER = "hasAnyRole('USER','TEACHER','ADMIN')"
        const val USER_URL = "/user/normal"
        const val USER_TEACHER = "/user/teacher"
        const val TEACHER = "hasAnyRole('TEACHER','ADMIN')"
        val USER_Role = arrayOf("USER", "ADMIN")
        const val Admin_Role = "USER"
        const val ADMIN = "hasRole('ADMIN')"
        //        const val ONLY_USER = "hasRole('USER')"
        const val USER_ADMIN = "hasRole('USER') AND hasRole('ADMIN')"
    }

    object PicType {
        //图片类型
        //0是发表动态 1是视频动态 2是饮食图片
        //多次签名
        val periodTypes = arrayOf("dynamic/pic_dynamic", "dynamic/video_dynamic","eat/pic")
        val oneTypes = arrayOf("avatar")
    }

    object COSURL {
        //图片类型
        //0是发表动态
        val picUrl = "http://kindergartens-1253631018.pictj.myqcloud.com"

    }
}
