package kindergarten.web.service

import kindergarten.ext.throwMessageException
import kindergarten.web.dao.KgSchoolDao
import kindergarten.web.entity.KgSchool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class SchoolService(@Autowired private val kgSchoolDao: KgSchoolDao) {

    fun getSchoolInfoById(schoolId: String): KgSchool {
        return kgSchoolDao.single(schoolId) ?: "暂无该学校记录$schoolId".throwMessageException()
    }

}