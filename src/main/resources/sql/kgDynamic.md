sample
===
* 注释

	select #use("cols")# from kg_message_list where #use("condition")#

selectDynamic
====
* 获取用户对应的校园信息 dynamic_type 0是班级

    SELECT * FROM kg_dynamic WHERE 1 = 1
    @if(dynamic_type == 0){
    	 and `classroom_id`=#selectId#
    @}else{
     and `school_id`=#selectId#
    @} 
    ORDER BY  create_time DESC LIMIT #offset# ,#max_size#
  	@ orm.many({"id":"dynamic_id"},"kgDynamic.selectDynamicPic","KgDynamicPics");
   
selectDynamicPic
====
* 查询动态的图片
    SELECT * FROM kg_dynamic_pics WHERE dynamic_id = #dynamic_id# ORDER BY sequence

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
	
