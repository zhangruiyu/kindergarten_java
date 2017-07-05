queryUser
===

* 根据手机号查找
    
    select #use("cols")# from kg_user where #use("condition")# 

queryUserAndRole
===

* 根据手机号查找用户和权限
    
    select u.id,u.tel,u.login_password
        ,r.role_name
    from kg_user u
        LEFT JOIN kg_role_user sru on u.id= sru.user_id
        LEFT JOIN kg_role r on sru.role_id=r.id
    where tel= #tel# 
  

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
	
