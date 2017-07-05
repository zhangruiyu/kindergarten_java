package kindergarten.web.entity

/**
 * Created by zhangruiyu on 2017/7/3.
 */
class KgMessageDynamic
(
        val id: Int,
        val messageText: String,
        val schoolId: Int,
        val classRoomId: Int,
        //可见类型  全园可见 ,班级可见   根据用户角色来判断
        val seeType: Int,
        //消息对应图片id 根绝时间排序
        val pictureId: Int,
        //点赞人名字
        val liked: List<String>,
        //点赞人id
        val likedId: List<String>
) {
}