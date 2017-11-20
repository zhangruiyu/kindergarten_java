sample
===
* 注释

	select #use("cols")# from kg_message_list where #use("condition")#

selectDynamic
====
* 获取用户对应的校园信息 dynamic_type 0是班级

   SELECT
     d.id,
     content,
     d.create_time,
     d.user_id,
     d.dynamic_type,
     p.pic_url,
     p.sequence,
     p.video_length,
     p.video_url,
     kdc.id as comment_id,
     kdc.comment_content,
     kdc.group_tag,
     kdc.parent_comment_id,
     kdc.user_id as comment_user_id,
     kdc.create_time as comment_create_time,
     kdc.dynamic_id as comment_dynamic_id
   FROM (SELECT *
              FROM kg_dynamic
              WHERE school_id = #selectId#
              ORDER BY create_time DESC
              LIMIT #offset# ,#max_size#)  d INNER JOIN kg_dynamic_content p ON d.id = p.dynamic_id LEFT JOIN kg_dynamic_comment kdc
                                             ON kdc.dynamic_id = d.id ORDER BY create_time DESC
    
commitDynamic
====
* 发布动态
   INSERT INTO kg_dynamic (school_id,id, classroom_id, content, visibility_type, user_id, dynamic_type)
   VALUES ( #schoolId#,#dynamicId#,#classroomId#,#dynamic_content#,#visibilityType#,#userId#,#dynamic_type#);
     
commitDynamicVideo
====
* 插入视频
   INSERT INTO kg_dynamic_content (dynamic_id, video_url, video_length, video_pic) SELECT #dynamicId#,#video_server_url#,#video_long#, #screenshot_server_url#
    
     
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
	
