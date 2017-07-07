package kindergarten

import java.io.Serializable

/**
 * Created by zhangruiyu on 2017/7/5.
 */
open class User : Serializable {

    var id: String? = null
    var id2: String? = null
    var id23: String? = null
    var id233: String? = null
    var id2334: String? = null

    constructor()
    constructor(id: String?, id2: String?, id23: String?, id233: String?, id2334: String?) {
        this.id = id
        this.id2 = id2
        this.id23 = id23
        this.id233 = id233
        this.id2334 = id2334
    }

}
