package kindergarten.web.controller

import kindergarten.annotation.PoKo
import kindergarten.custom.CustomConstants
import kindergarten.ext.jsonOk
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
//@PreAuthorize(value = CustomConstants.CustomPermission.ADMIN)
@PoKo class ViewController {

    @RequestMapping("/")
            //    @PreAuthorize("hasRole('ADMIN')")
    fun index(): String {
        return CustomConstants.MessageConstants.sendMessageCode().jsonOk()
    }
}