package kindergarten.comm.rest.entity

import kindergarten.annotation.PoKo

class YSToken : YSBasicEntity<YSToken.Data>() {
    @PoKo
    data class Data(var accessToken: String,
                    var expireTime: Long)
}