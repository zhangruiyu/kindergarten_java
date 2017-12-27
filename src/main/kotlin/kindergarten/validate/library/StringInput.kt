package kindergarten.validate.library

class StringInput(var param: String, var failsMessage: String = "") : Input {
    override fun getValue(): String = param

}