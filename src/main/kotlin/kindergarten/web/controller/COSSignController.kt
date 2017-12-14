package kindergarten.web.controller

import com.qcloud.cos.sign.Credentials
import com.qcloud.cos.sign.Sign
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import kindergarten.comm.vals.CustomConstants
import kindergarten.config.cos.OCSConfig
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.entity.custom.COSSignInfo
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * Created by zhangruiyu on 2017/7/18.
 */
@RestController
class COSSignController(private val ocsConfig: OCSConfig) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    private val prefix = "/pic"
    //1是发表动态图片
    @PostMapping(value = ["/user/cos/periodEffectiveSign"])
    @ApiOperation(value = "获取腾讯对象存储sign", notes = "传入上传类型")
    @ApiImplicitParams(ApiImplicitParam(name = "type", value = "0是图片动态 1是视频动态", required = true, dataType = "Int"))
    fun getCoSSign(@RequestParam(required = true) type: Int): ResponseData? {
        val cred = Credentials(ocsConfig.AppId!!, ocsConfig.SecretId, ocsConfig.SecretKey)
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val cosPath: String = "$prefix/${CustomConstants.PicType.periodTypes[type]}/${jwt.id}"
        val expired = System.currentTimeMillis() / 1000 + 3600
        val periodEffectiveSign = Sign.getPeriodEffectiveSign(ocsConfig.BucketName, cosPath, cred, expired)
        return COSSignInfo(periodEffectiveSign, cosPath).jsonOk()
    }

    @PostMapping(value = ["/user/cos/oneEffectiveSign"])
    @ApiOperation(value = "获取腾讯对象存储sign", notes = "传入上传类型")
    @ApiImplicitParams(ApiImplicitParam(name = "type", value = "0是头像", required = true, dataType = "Int"))
    fun getSCoSSign(@RequestParam(required = true) type: Int): ResponseData? {
        val cred = Credentials(ocsConfig.AppId!!, ocsConfig.SecretId, ocsConfig.SecretKey)
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val cosPath: String = "$prefix/${CustomConstants.PicType.oneTypes[type]}/${jwt.id}.jpg"
        val periodEffectiveSign = Sign.getOneEffectiveSign(ocsConfig.BucketName, cosPath, cred)
        return COSSignInfo(periodEffectiveSign, cosPath).jsonOk()
    }
}