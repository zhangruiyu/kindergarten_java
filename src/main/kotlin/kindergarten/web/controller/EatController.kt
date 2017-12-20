package kindergarten.web.controller

import com.xiaoleilu.hutool.date.DateUtil
import com.zhangruiyu.github.youeryuanxiaozhushou.ValueScheme
import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.config.SpringParamsValidate
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.AuthService
import kindergarten.web.service.EatService
import org.slf4j.LoggerFactory
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
@Api(description = "饮食消息")
class EatController(
        @Autowired private var mPassportService: AuthService,
        @Autowired private var eatService: EatService
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = [(CustomConstants.CustomPermission.USER_URL)+"/eat/eatList"])
    @PreAuthorize(CustomConstants.CustomPermission.USER)
    fun getEatListByDate(@RequestParam(required = true) date: String): Callable<ResponseData> {
        return Callable {
            val formatDate = DateUtil.format(DateUtil.parse(date), "yyyy-MM") + "%"
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            eatService.getEatList(mPassportService.getKgProfile(jwt.id).schoolId!!, formatDate).jsonOk()
        }
    }

    @PostMapping(value = [(CustomConstants.CustomPermission.USER_TEACHER)+"/eat/addEat"])
    @PreAuthorize(CustomConstants.CustomPermission.TEACHER)
    fun addEat(@RequestParam(required = true) date: String,
               @RequestParam(required = true) breakfast: String,
               @RequestParam(required = true) lunch: String,
               @RequestParam(required = true) supper: String,
               @RequestParam(required = false) urls: String?): Callable<ResponseData> {
        return Callable {
            SpringParamsValidate().add(breakfast, "早餐内容不能为空", ValueScheme.Required())
                    .add(lunch, "午餐内容不能为空", ValueScheme.Required())
                    .add(supper, "下午加餐内容不能为空", ValueScheme.Required()).test()
            val formatDate = DateUtil.format(DateUtil.parse(date), "yyyy-MM-dd")
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            try {
                return@Callable eatService.addEat(mPassportService.getKgProfile(jwt.id).schoolId!!, formatDate
                        , breakfast, lunch, supper, urls, formatDate).jsonOk()
            } catch (e: Exception) {
                return@Callable "今日数据已经存在".jsonNormalFail()
            }

        }
    }
}