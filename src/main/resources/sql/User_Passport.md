queryUser
===

* 根据手机号查找
    
    select #use("cols")# from User_Passport where #use("condition")# 
   
queryUserAndRole
===

* 根据手机号查找用户和权限
    
    select u.passport_id,u.tel,u.login_password,u.last_password_reset_date
        ,r.role_name
    from User_Passport u
        LEFT JOIN Use_Role_Passport sru on u.passport_id= sru.passport_id
        LEFT JOIN User_Role r on sru.role_id=r.role_id
    where tel= #tel# 
  

insertUserRole
===

* 插入权限

    INSERT INTO Use_Role_Passport (passport_id, role_id) VALUES (#passport_id#,#role_id#)

insertPassport
===

    INSERT INTO User_Passport(tel,login_password) values (#tel#,#login_password#)

insertProfile
===

   INSERT INTO User_Profile(passport_id,tel,register_ip) VALUES (#passport_id#,#tel#,#register_ip#)

cols
===

	passport_id,tel,login_password,last_password_reset_date,wx_open_id

updateSample
===

	`passport_id`=#passport_id#,`tel`=#tel#,`login_passwor*`****`*d`=#login_password#,`wx_open_id`=#wx_open_id#,`last_password_reset_date`=#last_password_reset_date#

condition
===

    1 = 1  
	@if(!isEmpty(passport_id)){
	 and `passport_id`=#passport_id#
	@}
	@if(!isEmpty(tel)){
	 and `tel`=#tel#
	@}
	@if(!isEmpty(login_password)){
	 and `login_password`=#login_password#
	@}
	@if(!isEmpty(wx_open_id)){
	 and `wx_open_id`=#wx_open_id#
	@}