package kindergarten.web.controller

import kindergarten.web.service.MessageListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@Controller
class ViewController {
    @Autowired
    lateinit var messageListService: MessageListService

    @GetMapping("/index.html")
    fun index(): ModelAndView {
        return ModelAndView("/index.html")
    }

}