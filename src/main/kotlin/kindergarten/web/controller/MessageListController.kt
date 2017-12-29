package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.comm.vals.CustomConstants.CustomPermission.TEACHER_URL
import kindergarten.comm.vals.CustomConstants.CustomPermission.USER_URL
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.MessageListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@Api(description = "学校消息")
class MessageListController(@Autowired private val messageListService: MessageListService) {

    @PostMapping(value = [USER_URL + "/messageList/schoolMessage"])
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun getMessageListBySchoolId(): Callable<ResponseData> {
        return Callable {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            messageListService.getMessageListBySchoolId(jwt.id).jsonOk()
        }
    }

    @PostMapping(value = [USER_URL + "/messageList/classroomMessage"])
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun getClassroomMessageListBySchoolId(): Callable<ResponseData> {
        return Callable {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            messageListService.getMessageListByClassroomId(jwt.id).jsonOk()
        }
    }

    @PostMapping(value = [TEACHER_URL + "/messageList/addClassroomMessage"])
    @PreAuthorize(CustomConstants.CustomPermission.TEACHER)
    fun addClassRoomMessage(@RequestParam(required = true) message: String): Callable<ResponseData> {
        return Callable {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            //1是校园消息
            messageListService.addClassRoomMessage(jwt.id, message, 1).jsonOk()
        }
    }
}