package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.custom.CustomConstants
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.MessageListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@RequestMapping(value = "/messageList")
@Api(description = "学校消息")
class MessageListController(@Autowired val messageListService: MessageListService) {

    @PostMapping(value = "/getSchoolMessage")
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun getMessageListBySchoolId(request: HttpServletRequest):Any {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        messageListService.getMessageListBySchoolId()
        return "1"
    }
}