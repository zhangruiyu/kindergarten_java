selectClassroomAndCamera
===
* 注释

	select #use("cols")# from kg_classroom where #use("condition")#
    @ orm.many({"id":"classroomId"},"kgClassroom.selectCameras","KgCamera");
    
selectCameras
====
* 查询对应教室的摄像头
    SELECT * FROM kg_camera WHERE classroom_id = #classroomId#  
  
cols
===

	id,school_id,show_name,classroom_image,child_count,is_corridor

updateSample
===

	`id`=#id#,`school_id`=#schoolId#,`show_name`=#showName#,`classroom_image`=#classroomImage#,`child_count`=#childCount#,`is_corridor`=#isCorridor#

condition
===

	1 = 1  
	@if(!isEmpty(schoolId)){
	 and `school_id`=#schoolId#
	@}
	@if(!isEmpty(showName)){
	 and `show_name`=#showName#
	@}
	@if(!isEmpty(classroomImage)){
	 and `classroom_image`=#classroomImage#
	@}
	@if(!isEmpty(childCount)){
	 and `child_count`=#childCount#
	@}
	@if(!isEmpty(isCorridor)){
	 and `is_corridor`=#isCorridor#
	@}
	