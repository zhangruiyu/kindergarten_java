package kindergarten.config

import kindergarten.ext.throwMessageException
import kindergarten.validate.library.MessageDisplay
import kindergarten.validate.library.ParamsValidate

class SpringParamsValidate : ParamsValidate() {
    init {
        setMessageDisplay(MessageDisplay { _, message -> message.throwMessageException() })
    }
}
