package kindergarten.comm.rest.entity

class YSRegisterEntity : YSBasicEntity<YSRegisterEntity.Data>() {
    data class Data(var accountId: String)
}