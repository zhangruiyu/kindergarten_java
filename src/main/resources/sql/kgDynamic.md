sample
===
* 注释

	select #use("cols")# from kg_message_list where #use("condition")#

selectDynamic
====
* 获取用户对应的校园信息 dynamic_type 0是班级

    SELECT id,content,create_time,d.user_id,p.nick_name FROM kg_dynamic d INNER JOIN kg_profile p ON 1 = 1
    @if(dynamic_type == 0){
    	 and  d.classroom_id = #selectId#
    @}else{
     and d.school_id = #selectId#
    @} 
    ORDER BY  create_time DESC LIMIT #offset# ,#max_size#
  	@ orm.many({"id":"dynamic_id"},"kgDynamic.selectDynamicPic","KgDynamicPics");
  	@ orm.many({"id":"dynamic_id"},"kgDynamic.selectDynamicLiked","KgDynamicLiked");
   
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
	
