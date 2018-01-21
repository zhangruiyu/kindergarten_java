package kindergarten.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@Controller
class ViewController {

    @GetMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("/index.html")
    }

    @GetMapping(value = ["/about.html", "/contact.html", "/gallery.html", "/index.html", "/shortcodes.html", "/single.html"])
    fun indexHtml(request: HttpServletRequest): ModelAndView {
        val url = request.requestURI.toString()
        return ModelAndView(url)
    }
}