package kindergarten.comm.rest.ys.entity

import kindergarten.annotation.PoKo

class YSToken : YSBasicEntity<YSToken.Data>() {
    @PoKo
    data class Data(var accessToken: String,
                    var expireTime: Long)
}