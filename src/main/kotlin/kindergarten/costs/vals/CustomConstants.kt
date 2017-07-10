package kindergarten.costs.vals

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.digest.HmacUtils


/**
 * Created by zhangruiyu on 2017/5/16.
 */
object CustomConstants {
    object CustomPermission {
        const val USER = "hasAnyRole('USER','ADMIN')"
        const val ADMIN = "hasRole('ADMIN')"
        //        const val ONLY_USER = "hasRole('USER')"
        const val USER_ADMIN = "hasRole('USER') AND hasRole('ADMIN')"

    }

    object Other {
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

}
