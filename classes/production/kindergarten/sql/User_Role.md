
selectUserAndRoles
===

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
	@if(!isEmpty(passport_id)){
	 and `passport_id`=#passport_id#
	@}