sample
===
* 注释

	select #use("cols")# from kg_profile where #use("condition")#

cols
===

	id,passport_id,tel,real_name,gender_id,birthday,address,avatar,add_time,school_ID,login_count,register_time,register_ip,last_login_time,last_login_ip

updateSample
===

	`id`=#id#,`passport_id`=#passportId#,`tel`=#tel#,`real_name`=#realName#,`gender_id`=#genderId#,`birthday`=#birthday#,`address`=#address#,`avatar`=#avatar#,`add_time`=#addTime#,`school_ID`=#schoolId#,`login_count`=#loginCount#,`register_time`=#registerTime#,`register_ip`=#registerIp#,`last_login_time`=#lastLoginTime#,`last_login_ip`=#lastLoginIp#

condition
===

	1 = 1  
	@if(!isEmpty(passportId)){
	 and `passport_id`=#passportId#
	@}
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
	 and `school_ID`=#schoolId#
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
	
