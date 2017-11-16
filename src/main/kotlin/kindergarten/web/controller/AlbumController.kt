package kindergarten.web.controller

import io.swagger.annotations.ApiOperation
import kindergarten.ext.ResponseData
import kindergarten.ext.jsonNormalFail
import kindergarten.ext.jsonOk
import kindergarten.security.JwtUserFactory
import kindergarten.web.dao.KgCameraDao
import kindergarten.web.dao.KgClassroomDao
import kindergarten.web.service.AlbumService
import kindergarten.web.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

/**
 * Created by zhangruiyu on 2017/7/18.
 */
@RestController
@RequestMapping(value = "user/album")
class AlbumController(@Autowired val albumService: AlbumService, @Autowired val authService: AuthService) {

    val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping(value = "/schoolAlbum")
    @ApiOperation(value = "获取校园相册", notes = "获取校园相册")
            //    @ApiImplicitParams(ApiImplicitParam(name = "t))
    fun schoolAlbum(): Callable<ResponseData>? {
        return Callable {
            val jwtUserAfterFilter = JwtUserFactory.getJwtUserAfterFilter()
            val kgProfile = authService.getKgProfile(jwtUserAfterFilter.id)
            if (kgProfile.schoolId.isNullOrEmpty()) {
                "请加入幼儿园后再次尝试".jsonNormalFail()
            } else {
                albumService.selectSchoolAlbum(kgProfile.schoolId!!).jsonOk()
            }

        }
    }

}