package kindergarten.web.entity

import com.alibaba.fastjson.annotation.JSONField
import org.beetl.sql.core.TailBean
import java.io.Serializable

/*
*
* gen by beetlsql 2017-06-20
*/
class KgUser : TailBean(), Serializable {
    @JSONField(serialize = false)
    var id: Int? = null
    @JSONField(serialize = false)
    var loginCount: Int? = null
    @JSONField(serialize = false)
    var loginPassword: String? = null
    @JSONField(serialize = false)
    var wxOpenId: String? = null

    var token: String? = null
    var tel: String? = null
    @JSONField(serialize = false)
    var roleName: String? = null

}
