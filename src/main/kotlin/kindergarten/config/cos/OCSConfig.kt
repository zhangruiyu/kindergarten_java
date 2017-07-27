package kindergarten.config.cos

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by zhangruiyu on 2017/7/18.
 */

@ConfigurationProperties(prefix = "Tencent.cos")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
class OCSConfig {
    var AppId: Long? = null
    var BucketName: String? = null
    var SecretId: String? = null
    var SecretKey: String? = null
    var TokenExpireTime: Long? = null
    var picPrefix: String? = null
}