package kindergarten.utils

import kindergarten.annotation.PoKo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

/**
 * Created by zhangruiyu on 2017/5/15.
 */
@Component
@PoKo
class PrivateBCryptPasswordEncoder : BCryptPasswordEncoder()
