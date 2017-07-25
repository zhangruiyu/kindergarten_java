package kindergarten.web.entity.custom

import kindergarten.annotation.PoKo
import java.io.Serializable

@PoKo
class DynamicPicUrl(val url: String, val position: String) : Serializable {

}