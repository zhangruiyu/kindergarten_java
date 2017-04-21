package kindergarten.config.interceptor

import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletResponse
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest


/**
 * Created by zhangruiyu on 2017/4/20.
 */
class MyInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var flag = true
        val token = request.getParameter("token")
        if (token.isEmpty()) {
            return false
        }
        response.sendRedirect("/toLogin")
        /*  val user = request.getSession().getAttribute("user") as User
          if (null == user) {
              response.sendRedirect("toLogin")
              flag = false
          } else {
              flag = true
          }*/
        return flag
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView) {
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception) {
    }
}
