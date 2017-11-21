package kindergarten.web.controller

import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOk
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
class CheckUploadController {

    @PostMapping("/checkUpdate")
            //    @PreAuthorize("hasRole('ADMIN')")
    fun checkUpdate(@RequestParam(required = true) version: Int): ResponseData {
        return if (version >= 1000) {
            CheckUpdate(0).jsonOk()
        } else {
            CheckUpdate(2, "1.修复若干bug\n2.优化视频模块", "http://gyxz.exmmw.cn/a3/rj_sp1/shizidaikuan.apk").jsonOk()
        }
    }

}

//0是不更新 1是更新 2是强制更新
class CheckUpdate(val checkState: Int, val message: String? = null, val downloadUrl: String? = null)