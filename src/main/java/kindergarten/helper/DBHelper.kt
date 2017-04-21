package kindergarten.helper

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder

/**
 * Created by zhangruiyu on 2017/4/18.
 */
class DBHelper private constructor() {
    val db = DataSourceBuilder.create().build()!!

    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder {
        var instance = DBHelper()
    }
}
