package kindergarten.helper

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment


/**
 * Created by zhangruiyu on 2017/4/18.
 */
class DBHelper private constructor() {

    val db = DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/kindergarten?useUnicode=true&characterEncoding=utf-8&useSSL=true&verifyServerCertificate=false").username("root").password("woaiwojia520").driverClassName("com.mysql.jdbc.Driver").build()

    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder {
        var instance = DBHelper()
    }
}
