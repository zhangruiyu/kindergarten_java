package kindergarten.web.dao

import kindergarten.web.entity.KgClassroom
import org.beetl.sql.core.annotatoin.SqlStatement
import org.beetl.sql.core.mapper.BaseMapper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository

@Repository
interface KgClassroomDao : BaseMapper<KgClassroom> {
    @SqlStatement(params = "schoolId")
    fun selectClassroomAndCamera(schoolId: String):ArrayList<KgClassroom>
}