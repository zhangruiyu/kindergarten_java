selectProfile
===
* 注释

	select #use("cols")# from kg_profile where #use("condition")#


insertProfile
===

   INSERT INTO kg_profile(user_id,tel,register_ip) VALUES (#passport_id#,#tel#,#register_ip#)

cols
===
  
  	user_id,tel,real_name,gender_id,birthday,address,avatar,add_time,school_id,login_count,register_time,register_ip,last_login_time,last_login_ip,classroom_id
  
updateSample
===
  
  	`user_id`=#userId#,`tel`=#tel#,`real_name`=#realName#,`gender_id`=#genderId#,`birthday`=#birthday#,`address`=#address#,`avatar`=#avatar#,`add_time`=#addTime#,`school_id`=#schoolId#,`login_count`=#loginCount#,`register_time`=#registerTime#,`register_ip`=#registerIp#,`last_login_time`=#lastLoginTime#,`last_login_ip`=#lastLoginIp#
  
condition
===
  
  	1 = 1  
  	@if(!isEmpty(tel)){
  	 and `tel`=#tel#
  	@}
  	@if(!isEmpty(realName)){
  	 and `real_name`=#realName#
  	@}
  	@if(!isEmpty(genderId)){
  	 and `gender_id`=#genderId#
  	@}
  	@if(!isEmpty(birthday)){
  	 and `birthday`=#birthday#
  	@}
  	@if(!isEmpty(address)){
  	 and `address`=#address#
  	@}
  	@if(!isEmpty(avatar)){
  	 and `avatar`=#avatar#
  	@}
  	@if(!isEmpty(addTime)){
  	 and `add_time`=#addTime#
  	@}
  	@if(!isEmpty(schoolId)){
  	 and `school_id`=#schoolId#
  	@}
  	@if(!isEmpty(loginCount)){
  	 and `login_count`=#loginCount#
  	@}
  	@if(!isEmpty(registerTime)){
  	 and `register_time`=#registerTime#
  	@}
  	@if(!isEmpty(registerIp)){
  	 and `register_ip`=#registerIp#
  	@}
  	@if(!isEmpty(lastLoginTime)){
  	 and `last_login_time`=#lastLoginTime#
  	@}
  	@if(!isEmpty(lastLoginIp)){
  	 and `last_login_ip`=#lastLoginIp#
  	@}
  	@if(!isEmpty(classroom_id)){
  	 and `classroom_id`=#classroom_id#
  	@}	
