package kindergarten.config

import com.zhangruiyu.github.youeryuanxiaozhushou.ParamsValidate
import kindergarten.ext.throwMessageException

class SpringParamsValidate : ParamsValidate() {
    fun SpringParamsValidate() {
        setMessageDisplay { input, message ->
            message.throwMessageException()
        }
    }
}
