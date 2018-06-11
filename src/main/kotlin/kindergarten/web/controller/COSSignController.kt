package kindergarten.web.controller

import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import kindergarten.comm.vals.CustomConstants
import kindergarten.comm.vals.CustomConstants.CustomPermission.USER_URL
import kindergarten.config.cos.OCSConfig
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.entity.custom.COSSignInfo
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.*
import java.util.TreeMap
import com.alibaba.druid.pool.DruidDataSourceFactory.config
import kindergarten.config.cos.StorageSts


/**
 * Created by zhangruiyu on 2017/7/18.
 */
@RestController
class COSSignController(private val ocsConfig: OCSConfig) {
    val logger = LoggerFactory.getLogger(this.javaClass)
    private val prefix = "/pic"
    //1是发表动态图片
    @PostMapping(value = ["$USER_URL/cos/periodEffectiveSign"])
    @ApiOperation(value = "获取腾讯对象存储sign", notes = "传入上传类型")
    @ApiImplicitParams(ApiImplicitParam(name = "type", value = "0是图片动态 1是视频动态", required = true, dataType = "Int"))
    fun getCoSSign(@RequestParam(required = true) type: Int): ResponseData? {
        val config = TreeMap<String, Any>()

// 您的 SecretID
        config["SecretId"] = ocsConfig.SecretId!!
// 您的 SecretKey
        config["SecretKey"] = ocsConfig.SecretKey!!
// 临时密钥有效时长，单位是秒，如果没有设置，默认是30分钟
        config["durationInSeconds"] = 1800
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val cosPath = "$prefix/${CustomConstants.PicType.periodTypes[type]}/${jwt.id}"
        val data  = StorageSts.getCredential(config).getJSONObject("data")

        val credential = data.getJSONObject("credentials").put("cosPath", cosPath).put("expiredTime", data.getLong("expiredTime")).toString()
//        val credential = StorageSts.getCredential(config)
//        val expired = System.currentTimeMillis() / 1000 + 3600
//        val cred = BasicCOSCredentials(ocsConfig.SecretId, ocsConfig.SecretKey)
//        val jwt = JwtUserFactory.getJwtUserAfterFilter()
//        val cosPath = "$prefix/${CustomConstants.PicType.periodTypes[type]}/${jwt.id}"
//        val periodEffectiveSign =  COSSigner().buildAuthorizationStr(HttpMethodName.POST, cosPath, cred, Date(expired))
        return credential.jsonOk()
    }
/*
    @PostMapping(value = ["$USER_URL/cos/oneEffectiveSign"])
    @ApiOperation(value = "获取腾讯对象存储sign", notes = "传入上传类型")
    @ApiImplicitParams(ApiImplicitParam(name = "type", value = "0是头像", required = true, dataType = "Int"))
    fun getSCoSSign(@RequestParam(required = true) type: Int): ResponseData? {
        val cred = BasicCOSCredentials( ocsConfig.SecretId, ocsConfig.SecretKey)
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        val cosPath: String = "$prefix/${CustomConstants.PicType.oneTypes[type]}/${jwt.id}.jpg"
        val expired = System.currentTimeMillis() / 1000 + 3600
        val periodEffectiveSign =  COSSigner().buildAuthorizationStr(HttpMethodName.POST, cosPath, cred, Date(expired))
        return COSSignInfo(periodEffectiveSign, cosPath).jsonOk()
    }*/
}