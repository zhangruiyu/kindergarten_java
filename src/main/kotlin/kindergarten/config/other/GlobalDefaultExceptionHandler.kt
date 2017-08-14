package kindergarten.config.other

import kindergarten.custom.MessageException
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOKNoData
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * Created by zhangruiyu on 2017/4/9.
 */
@ControllerAdvice
open class GlobalDefaultExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = MessageException::class)
    fun defaultValidateFailedException(req: HttpServletRequest, e: MessageException): ResponseData {
        e.printStackTrace()
        return if (e.code == 200) {
            e.message.jsonOKNoData()
        } else {
            e.message.jsonNormalFail(code = e.code)
        }

    }

    @ResponseBody
    @ExceptionHandler(value = Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): Any {
        //      // If the exception is annotated with @ResponseStatus rethrow it and let
        //      // the framework handle it - like the OrderNotFoundException example
        //      // at the start of this post.
        //      // AnnotationUtils is a Spring Framework utility class.
        //      if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
        //          throw e;
        //
        //      // Otherwise setup and send the user to a default error-view.
        //      ModelAndView mav = new ModelAndView();
        //      mav.addObject("exception", e);
        //      mav.addObject("url", req.getRequestURL());
        //      mav.setViewName(DEFAULT_ERROR_VIEW);
        //      return mav;

        //打印异常信息：
        e.printStackTrace()

        /*
        * 返回json数据或者String数据：
        * 那么需要在方法上加上注解：@ResponseBody
        * 添加return即可。
        */
        return "{error:${e.localizedMessage}}"
        /*
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl,error.jsp);
        *
        */
    }
}