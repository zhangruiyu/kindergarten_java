sample
===
* 注释

	select #use("cols")# from kg_classroom where #use("condition")#

cols
===

	id,school_id,title,classroom_image,chirld_count

updateSample
===

	`id`=#id#,`school_id`=#schoolId#,`title`=#title#,`classroom_image`=#classroomImage#,`chirld_count`=#chirldCount#

condition
===

	1 = 1  
	@if(!isEmpty(schoolId)){
	 and `school_id`=#schoolId#
	@}
	@if(!isEmpty(title)){
	 and `title`=#title#
	@}
	@if(!isEmpty(classroomImage)){
	 and `classroom_image`=#classroomImage#
	@}
	@if(!isEmpty(chirldCount)){
	 and `chirld_count`=#chirldCount#
	@}
	
