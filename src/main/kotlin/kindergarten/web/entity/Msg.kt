package kindergarten.web.entity

import kindergarten.annotation.PoKo

/**
 * Created by zhangruiyu on 2017/5/14.
 */
@PoKo class Msg {
    private var title: String? = null
    private var content: String? = null
    private var etraInfo: String? = null

    constructor(title: String, content: String, etraInfo: String) {
        this.title = title
        this.content = content
        this.etraInfo = etraInfo
    }
}