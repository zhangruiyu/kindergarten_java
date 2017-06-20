package kindergarten.custom

/**
 * Created by zhangruiyu on 2017/5/16.
 */
object CustomConstants {
    object CustomPermission {
        const val USER = "hasAnyRole('USER','ADMIN')"
        const val ADMIN = "hasRole('ADMIN')"
        const val USER_ADMIN = "hasRole('USER') AND hasRole('ADMIN')"
    }
}
