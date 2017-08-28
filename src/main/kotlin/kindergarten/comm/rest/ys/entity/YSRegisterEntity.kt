package kindergarten.comm.rest.ys.entity

import kindergarten.annotation.PoKo

@PoKo
class YSRegisterEntity : YSBasicEntity<YSRegisterEntity.Data>() {
    @PoKo
    data class Data(var accountId: String)
}