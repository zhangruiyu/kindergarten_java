package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.costs.vals.CustomConstants
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.MessageListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@RequestMapping(value = "/messageList")
@Api(description = "学校消息")
class MessageListController(@Autowired val messageListService: MessageListService) {

    @PostMapping(value = "/getSchoolMessage")
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun getMessageListBySchoolId(): Any {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return messageListService.getMessageListBySchoolId(jwt!!.id!!).jsonOk()

    }
}