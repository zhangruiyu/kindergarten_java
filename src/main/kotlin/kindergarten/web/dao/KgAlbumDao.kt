package kindergarten.web.dao

import kindergarten.web.entity.KgAlbum
import kindergarten.web.entity.KgDynamic
import org.beetl.sql.core.annotatoin.Sql
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

/**
 * Created by zhangruiyu on 2017/7/11.
 */
@Repository
interface KgAlbumDao : BaseMapper<KgAlbum> {

    @Sql(value = "SELECT kd.id,kdp.pic_url,kdp.sequence,kd.create_time FROM kg_dynamic kd INNER JOIN kg_dynamic_content kdp ON kd.school_id = ? AND kd.id = kdp.dynamic_id")
    fun selectSchoolAlbum(school_id: String): ArrayList<KgAlbum>

}