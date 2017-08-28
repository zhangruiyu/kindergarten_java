sample
===
* 注释

	select #use("cols")# from kg_message_list where #use("condition")#

selectDynamic
====
* 获取用户对应的校园信息 dynamic_type 0是班级

    SELECT d.id,content,create_time,d.user_id,p.nick_name FROM kg_dynamic d INNER JOIN kg_profile p ON  d.user_id = p.user_id
    @if(dynamic_type == 0){
    	 and  d.classroom_id = #selectId#
    @}else{
     and d.school_id = #selectId#
    @} 
    ORDER BY  create_time DESC LIMIT #offset# ,#max_size#
  	@ orm.many({"id":"dynamic_id"},"kgDynamic.selectDynamicPic","KgDynamicPics");
  	@ orm.many({"id":"dynamic_id"},"kgDynamic.selectDynamicLiked","KgDynamicLiked");
  	@ orm.many({"id":"dynamic_id"},"kgDynamic.examineComment","KgDynamicComment");

selectDynamicPic
====
* 查询动态的图片
    SELECT * FROM kg_dynamic_pics WHERE dynamic_id = #dynamic_id# ORDER BY sequence
  
selectDynamicLiked
====
* 查询点赞动态
    SELECT
      nick_name,
      l.user_id
    FROM kg_profile p INNER JOIN kg_dynamic_liked l ON l.user_id = p.user_id
    WHERE l.dynamic_id = #dynamic_id#
    ORDER BY l.create_time DESC
    
commitDynamic
====
* 发布动态
   INSERT INTO kg_dynamic (school_id,dynamic_id, classroom_id, content, visibility_type, user_id, dynamic_type)
   VALUES ( #schoolId#,#dynamicId#,#classroomId#,#dynamic_content#,#visibilityType#,#userId#,#dynamic_type#);
     
commitDynamicVideo
====
* 插入视频
   INSERT INTO kg_dynamic_video (dynamic_id, video_url, video_length, video_pic) SELECT LAST_INSERT_ID(),#video_server_url#,#video_long#, #screenshot_server_url#
    
     
examineComment
====
* 查看评论

    SELECT
    	id,
    	user_id,
    	dynamic_id,
    	comment_content,
    	create_time,
    	parent_comment_id,
    	group_tag
    FROM kg_dynamic_comment
    WHERE dynamic_id =  #dynamic_id# ORDER BY create_time


commitComment
====
* 添加评论


    INSERT INTO kg_dynamic_comment (user_id, dynamic_id, comment_content, state, parent_comment_id, group_tag)
    VALUES ()

cols
===

	id,school_id,message

updateSample
===

	`id`=#id#,`school_id`=#schoolId#,`message`=#message#

condition
===

	1 = 1  
	@if(!isEmpty(schoolId)){
	 and `school_id`=#schoolId#
	@}
	@if(!isEmpty(message)){
	 and `message`=#message#
	@}
	
