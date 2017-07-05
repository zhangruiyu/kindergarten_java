package kindergarten.custom

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

/**
 * Created by zhangruiyu on 2017/5/15.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class PrivateBCryptPasswordEncoder : BCryptPasswordEncoder()
