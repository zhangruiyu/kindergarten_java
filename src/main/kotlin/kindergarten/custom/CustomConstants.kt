package kindergarten.custom

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.digest.HmacUtils
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest
import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.IAcsClient
import com.aliyuncs.profile.DefaultProfile
import com.aliyuncs.profile.IClientProfile
import kindergarten.utils.RandomUtils


/**
 * Created by zhangruiyu on 2017/5/16.
 */
object CustomConstants {
    object CustomPermission {
        const val USER = "hasAnyRole('USER','ADMIN')"
        const val ADMIN = "hasRole('ADMIN')"
        const val USER_ADMIN = "hasRole('USER') AND hasRole('ADMIN')"

        val Bucket = "kindergartens"
        val APPID = "1253631018"
        val SecretID = "AKIDJXZP5QMPCqIYcUzlCSWP8h6JKBrLfljW"
        val SecretKey = "icP1Wt9IF1bzBQWQ8e0EqJZdbS9fz87x"
        //        var dan = "a=$APPID&b=$Bucket&k=$SecretID&e=0&t=${System.currentTimeMillis()/1000}&r=[rand]&u=0&f=[fileid]"
        fun sign(expired: Long): String {
            //a=[appid]&b=[bucket]&k=[SecretID]&t=[currenTime]&e=[expiredTime]

            val now = System.currentTimeMillis() / 1000
            val plain_text = String.format("a=%s&b=%s&k=%s&t=%s&e=%s",
                    APPID, Bucket, SecretID, now, expired)

            val bin = HmacUtils.hmacSha1(SecretKey, plain_text)

            var all = ByteArray(bin.size + plain_text.toByteArray().size)
            System.arraycopy(bin, 0, all, 0, bin.size)
            System.arraycopy(plain_text.toByteArray(), 0, all, bin.size, plain_text.toByteArray().size)

            all = Base64.encodeBase64(all)
            return String(all)
        }
    }

    object MessageConstants {
        const val Access_Key_ID = "LTAI0FKko2gacOlC"
        const val Access_Key_Secret = "GS2KAt1AANtdCX5GCYdlQKg7xheLNk"
        var acsClient: DefaultAcsClient

        init {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000")
            System.setProperty("sun.net.client.defaultReadTimeout", "10000")
//初始化ascClient需要的几个参数
            val product = "Dysmsapi"//短信API产品名称
            val domain = "dysmsapi.aliyuncs.com"//短信API产品域名
//替换成你的AK
            val accessKeyId = MessageConstants.Access_Key_ID//你的accessKeyId,参考本文档步骤2
            val accessKeySecret = MessageConstants.Access_Key_Secret//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region
            val profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret)
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain)
            acsClient = DefaultAcsClient(profile)
        }

        fun sendMessageCode(): String? {
            //组装请求对象
            val request = SendSmsRequest()
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.phoneNumbers = "15201231801"
            //必填:短信签名-可在短信控制台中找到
            request.signName = "幼儿园小助手"
            //必填:短信模板-可在短信控制台中找到
            request.templateCode = "SMS_73930004"
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.templateParam = "{\"code\":\"${RandomUtils.getRandNum()}\"}"
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.outId = "yourOutId"
            //请求失败这里会抛ClientException异常
            val sendSmsResponse = acsClient.getAcsResponse<SendSmsResponse>(request)
            if (sendSmsResponse.code != null && sendSmsResponse.code == "OK") {
                //请求成功
                return "验证码发送成功"
            } else {
                throw MessageException(sendSmsResponse.code)
            }
        }
    }
}
