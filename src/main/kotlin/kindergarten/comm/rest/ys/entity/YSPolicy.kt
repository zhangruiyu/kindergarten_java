package kindergarten.comm.rest.ys.entity

//萤石云Policy类 直接实现就可以生成对应全权限json
class YSPolicy {
    var statements: ArrayList<YSStatement> = ArrayList()
    override fun toString(): String = "{\"Statement\": [\n${statements.joinToString(",")}]}"
}

class YSStatement(var permission: DeviceYSPermission, var resource: DeviceYSResource) {
//    val tag = "DeviceYsPermission"
    override fun toString(): String {
        return "{$permission$resource}\n"
    }
}

class DeviceYSPermission {
    val tag = "Permission"
    val content: ArrayList<String> = ArrayList()
    override fun toString(): String {
        return "\"$tag\":" + "\"${content.joinToString(",")}\","
    }

    fun addPatriarchPermission(): DeviceYSPermission {
        content.add("Get")
        content.add("Real")
        content.add("Replay")
        return this
    }
}

class DeviceYSResource {
    val tag = "Resource"
    val content: ArrayList<String> = ArrayList()
    override fun toString(): String {
        return "\"$tag\":" + "[${content.joinToString(",")}]"
    }

    fun addDev(devId: String): DeviceYSResource {
        content.add("dev:" + devId)
        return this
    }
}

fun main(args: Array<String>) {
    val policy = YSPolicy()
    val statement = YSStatement(DeviceYSPermission().addPatriarchPermission(), DeviceYSResource().addDev("655816720"))
    policy.statements.add(statement)
    policy.statements.add(statement)
    print(policy)
}