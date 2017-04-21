package kindergarten.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@Controller
open class ViewController {

    @GetMapping(value = "/view/{user}")
    fun register(@PathVariable user: Int): ModelAndView {
        val view = ModelAndView("/index.btl")
        return view
    }
}