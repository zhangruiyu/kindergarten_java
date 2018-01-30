package kindergarten.web.controller

import io.swagger.annotations.Api
import kindergarten.comm.vals.CustomConstants
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.service.ProfileService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

/**
 * Created by zhangruiyu on 2017/7/3.
 */
@RestController
@Api(description = "手机号列表")
class TelsController(
        @Autowired private var mPassportService: ProfileService,
        @Autowired private var profileService: ProfileService
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @PostMapping(value = [(CustomConstants.CustomPermission.TEACHER_URL) + "/teachersAndStudentsInfo"])
    @PreAuthorize(CustomConstants.CustomPermission.TEACHER)
    fun getTeachersAndStudentsInfo(): Callable<ResponseData> {
        return Callable {
            val jwt = JwtUserFactory.getJwtUserAfterFilter()
            val kgProfile = profileService.getKgProfile(jwt.id)
            if (kgProfile.schoolId?.isNotEmpty() == true && kgProfile.classroomId?.isNotEmpty() == true) {
                val telsByClassroom = profileService.getTelsByClassroom(kgProfile.schoolId!!, kgProfile.classroomId!!)
                telsByClassroom.jsonOk()
            } else {
                "".jsonOk()
            }
        }
    }
}