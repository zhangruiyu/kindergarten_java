package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.DynamicService
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
@RequestMapping(value = "/dynamic")
@Api(description = "幼儿园动态")
class DynamicController(@Autowired val dynamicService: DynamicService) {

    @PostMapping(value = "/getSchoolMessage")
    @PreAuthorize(CustomConstants.CustomPermission.USER)
            //默认是0  获取的是班级的动态  1是全校
    fun getMessageListBySchoolId(@RequestParam(defaultValue = "0") dynamic_type: Int,
                                 @RequestParam(defaultValue = "0") page_index: Int,
                                 @RequestParam(defaultValue = "5") page_size: Int): Any {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val dynamic = dynamicService.getDynamic(jwt, dynamic_type, page_index, page_size)
        print(dynamic)
        return dynamic.jsonOk()

    }
}