
sample
===

	select #use("cols")# from User_Passport where #use("condition")#

cols
===

	passport_id,tel,login_password,wx_open_id,login_count,register_time,register_ip,last_login_time,last_login_ip

updateSample
===

	`passport_id`=#passport_id#,`tel`=#tel#,`login_password`=#login_password#,`wx_open_id`=#wx_open_id#,`login_count`=#login_count#,`register_time`=#register_time#,`register_ip`=#register_ip#,`last_login_time`=#last_login_time#,`last_login_ip`=#last_login_ip#

condition
===

	1 = 1  
	@if(!isEmpty(tel)){
	 and `tel`=#tel#
	@}
	@if(!isEmpty(login_password)){
	 and `login_password`=#login_password#
	@}
	@if(!isEmpty(wx_open_id)){
	 and `wx_open_id`=#wx_open_id#
	@}
	@if(!isEmpty(login_count)){
	 and `login_count`=#login_count#
	@}
	@if(!isEmpty(register_time)){
	 and `register_time`=#register_time#
	@}
	@if(!isEmpty(register_ip)){
	 and `register_ip`=#register_ip#
	@}
	@if(!isEmpty(last_login_time)){
	 and `last_login_time`=#last_login_time#
	@}
	@if(!isEmpty(last_login_ip)){
	 and `last_login_ip`=#last_login_ip#
	@}