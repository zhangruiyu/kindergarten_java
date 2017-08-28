package kindergarten.web.controller

import kindergarten.web.entity.KgMessageList
import kindergarten.web.service.MessageListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
//@PreAuthorize(value = CustomConstants.CustomPermission.ADMIN)
class ViewController {
    @Autowired
    lateinit var messageListService: MessageListService

    @PostMapping("/")
            //    @PreAuthorize("hasRole('ADMIN')")
    fun index(): List<KgMessageList> {
//        return CustomConstants.sendMessageCode().jsonOk()
        return messageListService.getMessageListBySchoolId("1")
    }

}