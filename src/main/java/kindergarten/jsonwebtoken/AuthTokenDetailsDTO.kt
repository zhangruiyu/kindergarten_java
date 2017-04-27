package kindergarten.jsonwebtoken

import java.util.*


/**
 * Created by zhangruiyu on 2017/4/24.
 */
class AuthTokenDetailsDTO {
    var userId: String? = null //id
    var tel: String? = null  //email
    var roleNames: List<String>? = null //roleNames

    //    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    var expirationDate: Date? = null
}
