sample
===
* 注释

	select #use("cols")# from kg_role where #use("condition")#

cols
===

	id,role_name

updateSample
===

	`id`=#id#,`role_name`=#roleName#

condition
===

	1 = 1  
	@if(!isEmpty(roleName)){
	 and `role_name`=#roleName#
	@}
	
