package kindergarten.web.controller

import kindergarten.annotation.PoKo
import kindergarten.utils.CustomConstants
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
@PreAuthorize(value = CustomConstants.CustomPermission.ADMIN)
@PoKo class ViewController {

    @RequestMapping("/")
    fun index(): Any {

        return 123
    }
}