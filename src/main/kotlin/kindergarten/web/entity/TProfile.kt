package kindergarten.web.entity

/**
 * Created by zhangruiyu on 2017/4/17.
 */
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.Date

/*
*
* gen by beetlsql 2017-04-17
*/

@Component
class TProfile : Serializable {
    var profileId: Int? = null
    var genderId: Int? = null
    var passportId: Int? = null
    var schoolId: Int? = null
    var address: String? = null
    var avatar: String? = null
    var realName: String? = null
    var tel: String? = null
    var wxOpenId: String? = null
    var addTime: Date? = null
    var birthday: Date? = null

}
