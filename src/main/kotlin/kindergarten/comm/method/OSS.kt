package kindergarten.comm.method


/**
 * Created by zhangruiyu on 2017/7/18.
 */
class OSS {
    companion object {
        const val appId = "10000"
        const val secretId = "AKIDJXZP5QMPCqIYcUzlCSWP8h6JKBrLfljW"
        const val secretKey = "icP1Wt9IF1bzBQWQ8e0EqJZdbS9fz87x"
        const val q_key_time = "1480932292;1517853898"
        const val host = "kindergartens-1253631018.costj.myqcloud.com"
//        val basicCOSCredentials = BasicCOSCredentials(appId, secretId, secretKey)
        fun g() {
/*
            "put\n[HttpURI]\n[HttpParameters]\n[HttpHeaders]\n"
            val SignKey =  HmacUtils.hmacSha1Hex(secretKey,q_key_time)
            val HttpString = "put\n/testfile\n\n$host=bucket1-1254000000.cn-north.myqcloud.com&x-cos-content-sha1=db8ac1c259eb89d4a131b253bacfca5f319d54f2&x-cos-stroage-class=nearline\n"
            val StringToSign = sha1\n1417773892;1417853898\nsha1($HttpString)\n
            val Signature = HmacUtils.hmacSha1Hex(SignKey,StringToSign)
//            val cred = Credentials(appId.toLong(), secretId, secretKey)
//            val clientConfig = ClientConfig()
//             设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
//            clientConfig.setRegion("tj")
//            val cosClient = COSClient(clientConfig, cred)*/
        }
    }
}