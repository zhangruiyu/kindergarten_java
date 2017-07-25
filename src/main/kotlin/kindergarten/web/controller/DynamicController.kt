package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
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
    val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = "/user/dynamic/getSchoolMessage")
            //默认是0  获取的是班级的动态  1是全校
    fun getMessageListBySchoolId(@RequestParam(defaultValue = "0") dynamic_type: Int,
                                 @RequestParam(defaultValue = "0") page_index: Int,
                                 @RequestParam(defaultValue = "5") page_size: Int): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val dynamic = dynamicService.getDynamic(jwt, dynamic_type, page_index, page_size)
        print(dynamic)
        return dynamic.jsonOk()

    }
/*
    @PostMapping(value = "/user/dynamic/commitDynamicPic")
            //默认是0  获取的是班级的动态  1是全校
    fun commitDynamic(@RequestParam(required = true) dynamic_content: String,
                      @RequestParam urls: ArrayList<String>?,
                      @RequestParam videoUrl: String?): Any {
        if (urls == null || urls.size == 0 || TextUtils.isEmpty(videoUrl?.trim())) {
            return "不能啥也不给".jsonNormalFail()
        }
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        if (videoUrl == null) {

        } else {
//            dynamicService.commitDynamic(kgProfile.userId, kgProfile.schoolId, kgProfile.classroomId, dynamic_content,
//                    urls)
        }

//        val dynamic = dynamicService.getDynamic(jwt, dynamic_type, page_index, page_size)
//        print(dynamic)
//        return dynamic.jsonOk()
        return 0
    }*/

    @PostMapping(value = "/user/dynamic/commitDynamicPic")
            //默认是0  获取的是班级的动态  1是全校
    fun commitDynamicPic(@RequestParam(required = true) dynamic_content: String,
                         @RequestParam(required = true) urls: String): Any {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()

        val dynamicPicUrls = urls.split(",").map {
            val split = it.split("*")
            DynamicPicUrl(split[0], split[1])
        }
        logger.debug("上传的图片拼接为 $urls")
        dynamicService.commitDynamicPic(jwt.id, dynamic_content, dynamicPicUrls)

//        val dynamic = dynamicService.getDynamic(jwt, dynamic_type, page_index, page_size)
//        print(dynamic)
//        return dynamic.jsonOk()
        return 0
    }

    @PostMapping(value = "/user/dynamic/commitDynamicVideo")
    fun commitDynamicVideo(@RequestParam(required = true) dynamic_content: String,
                           @RequestParam(required = true) screenshot_server_url: String,
                           @RequestParam(required = true) video_server_url: String,
                           @RequestParam(required = true) video_long: String
    ): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return dynamicService.commitDynamicVideo(jwt.id, dynamic_content,
                screenshot_server_url, video_server_url, video_long)

    }
}