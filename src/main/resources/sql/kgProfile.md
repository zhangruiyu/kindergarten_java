selectProfile
===
* 注释

	select #use("cols")# from kg_profile where #use("condition")#


insertProfile
===

   INSERT INTO kg_profile(user_id,tel,register_ip) VALUES (#passport_id#,#tel#,#register_ip#)

getKgProfileByQQORWeiXin
===
    SELECT * FROM kg_user WHERE
    1=1
    @if(platform == "QQ"){
    and qq_open_id = #uid#
    @} 
    @if(platform == "WEIXIN"){
    and wx_open_id = #uid#
    @} 


updateQQORWechat
===

*更新qq或者微信信息

    UPDATE kg_profile
    SET 
    @if(platform == "QQ"){
      qq_nick_name = #nickName#
     @} 
     @if(platform == "WEIXIN"){
      wx_nick_name = #nickName#
     @} 
     @if(avatarUrl != ""){
      avatar = #avatarUrl#
     @} 
    WHERE user_id = #id#


getTelsByClassroom
===

*获取班级里的所有人的手机号   如果是老师(role 3),那么久要只查询学校  如果是学生(role 2)那么需要查询班级和学校 因为只可看本班同学

    SELECT
      kru.role_id,
      kp.real_name,
      avatar,
      kc.show_name
    FROM kg_role_user kru INNER JOIN kg_profile kp
        ON (kru.role_id = 2 AND kru.user_id = kp.user_id AND kp.classroom_id = #classroomId# AND kp.school_id = #schoolId#) OR
           (kru.role_id = 3 AND kru.user_id = kp.user_id AND kp.school_id = #schoolId#)
        LEFT JOIN kg_classroom kc ON kp.classroom_id = kc.id
    
cols
===
  
  	user_id,tel,real_name,birthday,address,avatar,add_time,school_id,login_count,register_time,register_ip,last_login_time,last_login_ip,classroom_id
  
updateSample
===
  
  	`user_id`=#userId#,`tel`=#tel#,`real_name`=#realName#,`birthday`=#birthday#,`address`=#address#,`avatar`=#avatar#,`add_time`=#addTime#,`school_id`=#schoolId#,`login_count`=#loginCount#,`register_time`=#registerTime#,`register_ip`=#registerIp#,`last_login_time`=#lastLoginTime#,`last_login_ip`=#lastLoginIp#
  
condition
===
  
  	1 = 1  
  	@if(!isEmpty(tel)){
  	 and `tel`=#tel#
  	@}
  	@if(!isEmpty(realName)){
  	 and `real_name`=#realName#
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
