sample
===
* 注释

	select #use("cols")# from Use_Role_Passport where #use("condition")#

cols
===

	passport_id,role_id,role_name

updateSample
===

	`passport_id`=#passport_id#,`role_id`=#role_id#,`role_name`=#role_name#

condition
===

	1 = 1  
	@if(!isEmpty(role_id)){
	 and `role_id`=#role_id#
	@}
	@if(!isEmpty(role_name)){
	 and `role_name`=#role_name#
	@}