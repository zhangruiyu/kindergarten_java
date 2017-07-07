package kindergarten.web.controller

import kindergarten.annotation.PoKo
import kindergarten.custom.CustomConstants
import kindergarten.ext.jsonOk
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
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
//        return CustomConstants.sendMessageCode().jsonOk()
        return "woaichi"
    }

}