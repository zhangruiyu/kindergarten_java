package kindergarten.web.service

import kindergarten.utils.OCSUtils
import kindergarten.web.dao.KgEatListDao
import kindergarten.web.entity.KgEat
import kindergarten.web.entity.custom.DynamicPicUrl
import org.beetl.sql.core.SQLManager
import org.beetl.sql.core.SQLReady
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.AlternativeJdkIdGenerator

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class EatService(@Autowired private val kgEatListDao: KgEatListDao,
                 @Autowired private val sqlManager: SQLManager) {

    fun getEatList(schoolId: String, formatDate: String): List<KgEat> {

        return kgEatListDao.getEatList(schoolId, formatDate).groupBy {
            it.id
        }.values.map {
            val eatList = ArrayList<String>()
            it.forEach { kgEat ->
                if (kgEat.eatUrl?.isNotEmpty() == true) {
                    eatList.add(kgEat.eatUrl!!)
                }

            }
            it[0].eatUrls.addAll(eatList)
            return@map it[0]
        }
    }

    @Transactional
    fun addEat(schoolId: String, formatDate: String?, breakfast: String, lunch: String, supper: String, urls: String?, formatDate1: String): Any {
        val eatId = AlternativeJdkIdGenerator().generateId().toString()

        if (urls?.isNotEmpty() == true) {
            val picUrls = urls.split(",").map {
                val split = it.split("*")
                DynamicPicUrl(OCSUtils.toLocation(split[0]), OCSUtils.toLocation(split[1]))
            }
            val sql = "INSERT INTO kg_eat_pic (eat_id,eat_url) VALUES ${
            picUrls.joinToString {
                "('$eatId','${it.url}')"
            }
            } "
            sqlManager.executeUpdate(SQLReady(sql))
        }
        val sql = "INSERT INTO kg_eat (id,school_id,breakfast,lunch,supper,create_time) VALUES ${
        "('$eatId','$schoolId','$breakfast','$lunch','$supper','$formatDate')"
        } "
        sqlManager.executeUpdate(SQLReady(sql))
        return "提交成功"
    }
}