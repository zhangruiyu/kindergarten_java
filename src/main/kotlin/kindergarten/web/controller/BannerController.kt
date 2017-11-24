package kindergarten.web.controller

import kindergarten.annotation.PoKo
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import org.beetl.sql.core.SQLManager
import org.beetl.sql.core.SQLReady
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
class BannerController(@Autowired private val sqlManager: SQLManager) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping("/background/api/addBanner")
    fun addBanner(@RequestBody bannerBean: BannerBean): ResponseData {
        logger.debug("aaaagad")
        logger.error("aaa2222agad")
        if (bannerBean.picUrl.startsWith("http")) {
            "请不要添加前缀".jsonNormalFail()
        }
        return if (bannerBean.picUrl.endsWith(".png")
                || bannerBean.picUrl.endsWith(".gif") || bannerBean.picUrl.endsWith(".jpg")
                || bannerBean.picUrl.endsWith(".bmp")
                       ) {
            val sql = "INSERT INTO kg_banner (pic_url,title) VALUES ('${bannerBean.picUrl}','${bannerBean.title}')"
            sqlManager.executeUpdate(SQLReady(sql))
            "banner添加成功".jsonOk()
        } else {
            "请传入正确格式图片".jsonNormalFail()
        }

    }

}

@PoKo
class BannerBean(val title: String, val picUrl: String)