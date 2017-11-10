package kindergarten.config

import com.zhangruiyu.github.youeryuanxiaozhushou.MessageDisplay
import com.zhangruiyu.github.youeryuanxiaozhushou.ParamsValidate
import kindergarten.ext.throwMessageException

class SpringParamsValidate : ParamsValidate() {
    init {
        setMessageDisplay(MessageDisplay { _, message -> message.throwMessageException() })
    }
}
