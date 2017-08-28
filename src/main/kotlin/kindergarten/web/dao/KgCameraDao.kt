package kindergarten.web.dao

import kindergarten.web.entity.KgCamera
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.stereotype.Repository

@Repository
interface KgCameraDao : BaseMapper<KgCamera> {

    @SqlStatement(params = "classroomId")
    fun selectAllFiled(classroomId: String): ArrayList<KgCamera>

}