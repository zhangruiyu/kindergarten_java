package kindergarten.web.controller

import com.xiaoleilu.hutool.date.DateUtil
import kindergarten.annotation.PoKo
import kindergarten.comm.vals.CustomConstants
import kindergarten.comm.vals.CustomConstants.CustomPermission.CAN_USE_TOKEN_URL
import kindergarten.comm.vals.CustomConstants.ShoppingPoint.FirstStart
import kindergarten.comm.vals.CustomConstants.ShoppingPoint.NothingPoint
import kindergarten.custom.CustomTailBean
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.security.JwtTokenUtil
import kindergarten.web.entity.custom.WrapperInfo
import org.beetl.sql.core.SQLManager
import org.beetl.sql.core.SQLReady
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest

/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
class BannerController(@Autowired private val sqlManager: SQLManager,
                       @Autowired
                       private var jwtTokenUtil: JwtTokenUtil,
                       @Autowired
                       private var stringRedisTemplate: StringRedisTemplate
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping("/background/api/addBanner")
    fun addBanner(@RequestBody bannerBean: BannerBean): ResponseData {
        logger.debug("aaaagad")
        logger.error("aaa2222agad")
        /*if (bannerBean.picUrl.startsWith("http")) {
            "请不要添加前缀".jsonNormalFail()
        }*/
        return if (bannerBean.picUrl.endsWith(".png")
                || bannerBean.picUrl.endsWith(".gif") || bannerBean.picUrl.endsWith(".jpg")
                || bannerBean.picUrl.endsWith(".bmp")
                       ) {
            val sql = "INSERT INTO kg_banner (pic_url,title,url) VALUES ('${bannerBean.picUrl}','${bannerBean.title}','${bannerBean.url}')"
            sqlManager.executeUpdate(SQLReady(sql))
            "banner添加成功".jsonOk()
        } else {
            "请传入正确格式图片".jsonNormalFail()
        }

    }

    @PostMapping(CAN_USE_TOKEN_URL+"/getBanner")
    fun getBanner(request: HttpServletRequest): ResponseData {
        val username = jwtTokenUtil.getUsernameFromHttpServletRequest(request)
        val addShoppingPoint = if (username!= null && username.isNotEmpty()) {
            val jwtUser = jwtTokenUtil.getJwtUser(request)
            if (jwtUser != null) {
                val opsForValue = stringRedisTemplate.opsForValue()
                val tag = opsForValue.get("FirstLogin:$username")
                //判断今天是否添加过了
                if (tag == null) {
                    val now = DateUtil.date()
                    val endOfDay = DateUtil.endOfDay(now)
                    //添加积分
                    sqlManager.executeUpdate(SQLReady("update kg_profile set  shopping_point = shopping_point + ${FirstStart} where user_id = '${jwtUser.id}';"))
                    opsForValue.set("FirstLogin:$username", DateUtil.now(), endOfDay.time - now.time, TimeUnit.MILLISECONDS)
                    FirstStart
                } else NothingPoint
            } else NothingPoint

        } else NothingPoint

        val sql = "SELECT * FROM kg_banner"
        return WrapperInfo(sqlManager.execute(SQLReady(sql), BannerReturnBean::class.java), addShoppingPoint).jsonOk()
    }

}

@PoKo
class BannerBean(val title: String, val picUrl: String, val url: String)

@PoKo
class BannerReturnBean : CustomTailBean()