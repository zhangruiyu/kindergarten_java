package kindergarten.web.service

import kindergarten.web.dao.KgEatListDao
import kindergarten.web.entity.KgEat
import kindergarten.web.entity.custom.WrapperInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class EatService(@Autowired private val kgEatListDao: KgEatListDao) {

    fun getEatList(schoolId: String, formatDate: String): List<KgEat> {

        return kgEatListDao.getEatList(schoolId, formatDate).groupBy {
            it.id
        }.values.map {
            val eatList = ArrayList<String>()
            it.forEach { kgEat ->
                kgEat.eatUrl?.run {
                    eatList.add(kgEat.eatUrl!!)
                }

            }
            it[0].eatUrls.addAll(eatList)
            return@map it[0]
        }
    }
}