package kindergarten.web.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import kindergarten.comm.vals.CustomConstants
import kindergarten.config.SpringParamsValidate
import kindergarten.custom.MessageException
import kindergarten.ext.*
import kindergarten.security.JwtUserFactory
import kindergarten.utils.OCSUtils
import kindergarten.validate.library.ValueScheme
import kindergarten.web.entity.custom.WrapperInfo
import kindergarten.web.service.PassportService
import kindergarten.web.service.ProfileService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import javax.servlet.http.HttpServletRequest


/**
 * Created by zhangruiyu on 2017/4/11.
 */
@RestController
//@RequestMapping(value = "/")
@Api(description = "登陆注册,修改密码操作")
class AuthController(
        @Autowired private var mPassportService: PassportService,
        @Autowired private var mProfileService: ProfileService
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)


    @PostMapping("/public/auth/login")
    @ApiOperation(value = "登录", notes = "")
    fun login(@RequestParam(required = true) tel: String, @RequestParam(required = true) password: String, @RequestParam(defaultValue = "") pushToken: String): Callable<ResponseData> {
        logger.debug("试着登陆")
        logger.error("试着登陆")
        logger.info("试着登陆")
        logger.warn("试着登陆")
        logger.trace("试着登陆")
        return Callable {
            (tel.isEmpty() || password.isEmpty()).yes {
                "手机号或者密码不能不填".jsonNormalFail()
            }.otherwise {
                        mPassportService.login(tel, password, pushToken).jsonOk()
                    }

        }

    }

    @PostMapping("/public/auth/register1")
    @ApiOperation(value = "注册第一步", notes = "根据手机号和密码注册")
    @ApiImplicitParams(ApiImplicitParam(name = "tel", value = "用户手机号", required = true, dataType = "Long"))
    fun register1(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String): ResponseData {
        return (tel.length > 11).yes {
            "手机号格式错误".jsonNormalFail()
        }.otherwise {
                    mPassportService.trySendAuthCode(tel, httpServletRequest.getIpAddr())
                }
    }

    @PostMapping("/public/auth/register2")
    @ApiImplicitParam(name = "tel", value = "用户手机号", required = true)
    @ApiOperation(value = "注册,验证验证码", notes = "")
    fun test(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String
             , @RequestParam(required = true) password: String
             , @RequestParam(required = true) authCode: String): ResponseData {
        (tel.isEmpty() || password.isEmpty() || authCode.isEmpty()).yes {
            "手机号,密码和验证码不能不填".throwMessageException()
        }.otherwise {
                    //从redis里查看验证码
                    /*   if (stringRedisTemplate.opsForValue().get("${ProfileService.authCodePrefix}:$tel") == authCode) {
                           mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
                           return "注册成功,请登录".jsonOKNoData()
                       } else {
                           "验证码错误".throwMessageException()
                       }*/
                    return if ("888888" == authCode) {
                        mPassportService.registerUser(tel, password, httpServletRequest.getIpAddr())
                        "注册成功,请登录".jsonOKNoData()
                    } else {
                        "验证码错误".jsonNormalFail()
                    }

                }
    }

    @PostMapping("${CustomConstants.CustomPermission.USER_URL}/auth/changePassword")
    @ApiOperation(value = "修改密码", notes = "老密码和新密码")
    fun changePassword(@RequestParam(required = true) oldPassword: String
                       , @RequestParam(required = true) newPassword: String): ResponseData {
        val paramsValidate = SpringParamsValidate()
        paramsValidate.add(oldPassword, "旧密码格式不符", ValueScheme.MinLength(6), ValueScheme.MaxLength(15))
                .add(newPassword, "新密码格式不符", ValueScheme.MinLength(6), ValueScheme.MaxLength(15)).test()
        return mPassportService.changePassword(oldPassword, newPassword)
    }

    @PostMapping("${CustomConstants.CustomPermission.USER_URL}/profile")
    @ApiOperation(value = "获取用户信息", notes = "")
    fun getProfile(httpServletRequest: HttpServletRequest): ResponseData {
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return mProfileService.getKgProfileAndRoleCode(jwt).jsonOk()
    }

    @PostMapping(value = [(CustomConstants.CustomPermission.USER_URL) + "/reviseProfile"])
    @ApiImplicitParams(ApiImplicitParam(name = "avatarUrl", value = "如果没修改 传入空字符串", required = true, dataType = "String"))
    @ApiOperation(value = "修改用户信息", notes = "")
    fun reviseProfile(httpServletRequest: HttpServletRequest, @RequestParam(required = true) checkGender: Int
                      , @RequestParam(required = true) relationCheck: Int,
                      @RequestParam(required = true) address: String,
                      @RequestParam(required = true) avatarUrl: String): ResponseData {
        if (relationCheck < 0 || relationCheck > 6 || checkGender < 0 || checkGender > 1) {
            return "数据格式不对,请联系管理人员".jsonNormalFail()
        }
        val jwt = JwtUserFactory.getJwtUserAfterFilter()
        return mProfileService.reviseProfile(jwt.id, checkGender, relationCheck, address, OCSUtils.toLocation(avatarUrl)).jsonOk()
    }

    @PostMapping("/public/qqWeixinLogin")
    @ApiOperation(value = "第三方登录", notes = "")
    @ApiImplicitParams(ApiImplicitParam(name = "avatarUrl", value = "如果没修改 传入空字符串", required = true, dataType = "String"))
    fun qqWexinLogin(httpServletRequest: HttpServletRequest, @RequestParam(required = true) uid: String, @RequestParam(required = true) name: String, @RequestParam(required = true) gender: String,
                     @RequestParam(required = true) iconurl: String, @RequestParam(required = true) platform: String,
                     @RequestParam(defaultValue = "") pushToken: String): ResponseData {
        val kgProfileByQQORWeiXin = mProfileService.getKgProfileByQQORWeiXin(uid, platform)
        //说明没有授权过这个号
        return if (kgProfileByQQORWeiXin == null) {
            return "请授权后再尝试".jsonNormalFail(MessageException.TRY_QQ_WECHAT_LOGIN_NOT_AUTH)
        } else {
            mPassportService.login(kgProfileByQQORWeiXin.tel!!, "", pushToken, true).jsonOk()
        }
    }

    @PostMapping("/public/verifyIsRegister")
    @ApiImplicitParams(ApiImplicitParam(name = "tel", value = "验证的手机号,1是老用户,0是新用户", required = true, dataType = "String"))
    @ApiOperation(value = "验证的手机号是新老用户", notes = "")
    fun verifyIsRegister(httpServletRequest: HttpServletRequest, @RequestParam(required = true) tel: String): ResponseData {
        val paramsValidate = SpringParamsValidate()
        paramsValidate.add(tel, "手机号长度应为11位", ValueScheme.MinLength(11), ValueScheme.MaxLength(11)).test()
        return if (mProfileService.getUserByTel(tel) != null) {
            WrapperInfo("1").jsonOk()
        } else {
            WrapperInfo("0").jsonOk()
        }
    }


    @PostMapping(value = [(CustomConstants.CustomPermission.USER_URL) + "/unbindQQORWechat"])
    @ApiImplicitParams(ApiImplicitParam(name = "platform", value = "解锁平台,只有qq 和 wechat 2个值", required = true, dataType = "String"))
    @ApiOperation(value = "解绑QQ或微信", notes = "")
    fun unbindQQORWechat(httpServletRequest: HttpServletRequest, @RequestParam(required = true) platform: String): ResponseData {
        return if (platform == "QQ" || platform == "WEIXIN") {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            mProfileService.unbindQQORWechat(jwt.id, platform).jsonOk()
        } else {
            "参数不对".jsonNormalFail()
        }

    }

    @PostMapping(value = [(CustomConstants.CustomPermission.USER_URL) + "/bindQQORWechat"])
    @ApiImplicitParams(ApiImplicitParam(name = "platform", value = "只有qq 和 wechat 2个值", required = true, dataType = "String"))
    @ApiOperation(value = "绑订QQ或微信", notes = "")
    fun bindQQORWechat(httpServletRequest: HttpServletRequest,
                       @RequestParam(required = true) platform: String,
                       @RequestParam(required = true) uid: String,
                       @RequestParam(required = true) nickName: String,
                       @RequestParam(required = true) gender: String,
                       @RequestParam(required = true) iconurl: String): ResponseData {
        return if (platform == "QQ" || platform == "WEIXIN") {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            mProfileService.bindQQORWechat(jwt, platform, uid, nickName, iconurl).jsonOk()
        } else {
            "参数不对".jsonNormalFail()
        }

    }
}