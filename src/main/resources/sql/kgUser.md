queryUser
===

* 根据手机号查找
    
    select #use("cols")# from kg_user where #use("condition")# 

queryUserAndRole
===

* 根据手机号查找用户和权限
    
    SELECT
      u.id,
      u.tel,
      u.login_password,
      u.wx_open_id,
      u.qq_open_id,
      r.role_name,
      pro.gender,
      pro.address,
      pro.relation,
      pro.qq_nick_name,
      pro.wx_nick_name,
      ks.school_name
    FROM (kg_user u
      LEFT JOIN kg_role_user sru ON u.id = sru.user_id
      LEFT JOIN kg_role r ON sru.role_id = r.id)
      LEFT JOIN kg_profile pro ON pro.user_id = u.id
      LEFT JOIN kg_school ks  ON pro.school_id = ks.id
    WHERE u.tel =  #tel#
  
updateProfile
===

*更新部分字段

    UPDATE kg_profile
    SET gender = #checkGender#, relation = #relationCheck#, address = #address#
    @if(!(avatarUrl=='')){
      , `avatar` = #avatarUrl#
    @}
    WHERE user_id = #id#

updateQQORWechatOpenid
===

*更新qq或者微信openid

    UPDATE kg_user
    SET 
    @if(platform=='QQ'){
      qq_open_id = #uid#
    @}
    @if(platform=='WEIXIN'){
       wx_open_id = #uid#
    @}
    WHERE id = #id#


insertUserRole
===

* 插入权限

    INSERT INTO kg_role_user (user_id, role_id) VALUES (#passport_id#,#role_id#)

insertPassport
===

    INSERT INTO kg_user(id,tel,login_password) values (#id#,#tel#,#login_password#)

insertProfile
===

   INSERT INTO kg_profile(user_id,tel,register_ip) VALUES (#passport_id#,#tel#,#register_ip#)


cols
===

	id,tel,login_password,wx_open_id,login_count

updateSample
===

	`id`=#id#,`tel`=#tel#,`login_password`=#loginPassword#,`wx_open_id`=#wxOpenId#,`login_count`=#loginCount#

condition
===

	1 = 1  
	@if(!isEmpty(tel)){
	 and `tel`=#tel#
	@}
	@if(!isEmpty(loginPassword)){
	 and `login_password`=#loginPassword#
	@}
	@if(!isEmpty(wxOpenId)){
	 and `wx_open_id`=#wxOpenId#
	@}
	@if(!isEmpty(loginCount)){
	 and `login_count`=#loginCount#
	@}
	
