sample
===
* 注释

	select #use("cols")# from kg_message_list where #use("condition")#

getMessage
====
* 获取用户对应的校园信息

    SELECT message,create_time FROM kg_message_list WHERE school_id = (SELECT id FROM kg_school WHERE
      id = (SELECT kg_profile.school_id FROM kg_profile WHERE kg_profile.user_id = #user_id#)) ORDER BY create_time
    
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
	
