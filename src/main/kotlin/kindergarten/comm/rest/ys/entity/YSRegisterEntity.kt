package kindergarten.comm.rest.ys.entity

class YSRegisterEntity : YSBasicEntity<YSRegisterEntity.Data>() {
    data class Data(var accountId: String)
}