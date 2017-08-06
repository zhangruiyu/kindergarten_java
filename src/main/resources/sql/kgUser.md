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
      r.role_name,
      pro.gender,
      pro.address,
      pro.relation
    FROM (kg_user u
      LEFT JOIN kg_role_user sru ON u.id = sru.user_id
      LEFT JOIN kg_role r ON sru.role_id = r.id)
      LEFT JOIN kg_profile pro ON pro.user_id = u.id
    WHERE u.tel =  #tel#
  
updateProfile
===

*更新部分字段

    UPDATE kg_profile
    SET gender = #checkGender#, relation = #relationCheck#, address = #address#, avatar = #avatarUrl#
    WHERE user_id = #id#

insertUserRole
===

* 插入权限

    INSERT INTO kg_role_user (user_id, role_id) VALUES (#passport_id#,#role_id#)

insertPassport
===

    INSERT INTO kg_user(tel,login_password) values (#tel#,#login_password#)

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
	
