package kindergarten.web.service

import kindergarten.web.dao.KgAlbumDao
import kindergarten.web.entity.custom.WrapperInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@Service
class AlbumService(@Autowired val kgAlbumDao: KgAlbumDao) {

    fun selectSchoolAlbum(schoolId: String): Any {
        val selectSchoolAlbum = kgAlbumDao.selectSchoolAlbum(schoolId)
//        selectSchoolAlbum.
        return selectSchoolAlbum.groupBy {
            LocalDateTime.from(it.createTime!!.toInstant().atZone(ZoneId.systemDefault())).toLocalDate().toString()
        }.map {
            WrapperInfo(it.key, it.value)
        }
    }
}