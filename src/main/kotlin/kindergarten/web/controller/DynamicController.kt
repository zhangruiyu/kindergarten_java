package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.utils.OCSUtils
import kindergarten.web.entity.custom.DynamicPicUrl
import kindergarten.web.service.AuthService
import kindergarten.web.service.DynamicService
import org.apache.http.util.TextUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@Api(description = "幼儿园动态")
class DynamicController(private val dynamicService: DynamicService) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = "/user/dynamic/list")
            //默认是0  获取的是班级的动态  1是全校
    fun dynamicList(@RequestParam(defaultValue = "0") dynamic_type: Int,
                              @RequestParam(defaultValue = "0") page_index: Int,
                              @RequestParam(defaultValue = "5") page_size: Int): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val dynamic = dynamicService.getDynamic(jwt, dynamic_type, page_index, page_size)
        print(dynamic)
        return dynamic.jsonOk()

    }

    @PostMapping(value = "/user/dynamic/commitDynamicPic")
            //默认是0  获取的是班级的动态  1是全校
    fun commitDynamicPic(@RequestParam(required = true) dynamic_content: String,
                         @RequestParam(required = true) urls: String): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()

        val dynamicPicUrls = urls.split(",").map {
            val split = it.split("*")
            DynamicPicUrl(OCSUtils.toLocation(split[0]), OCSUtils.toLocation(split[1]))
        }
        logger.debug("上传的图片拼接为 $urls")


//        val dynamic = dynamicService.getDynamic(jwt, dynamic_type, page_index, page_size)
//        print(dynamic)
//        return dynamic.jsonOk()
        return dynamicService.commitDynamicPic(jwt.id, dynamic_content, dynamicPicUrls)
    }

    @PostMapping(value = "/user/dynamic/commitDynamicVideo")
    fun commitDynamicVideo(@RequestParam(required = true) dynamic_content: String,
                           @RequestParam(required = true) screenshot_server_url: String,
                           @RequestParam(required = true) video_server_url: String,
                           @RequestParam(required = true) video_long: String
    ): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return dynamicService.commitDynamicVideo(jwt.id, dynamic_content,
                OCSUtils.toLocation(screenshot_server_url), OCSUtils.toLocation(video_server_url), video_long)

    }
}