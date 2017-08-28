selectAllFiled
===
* 注释

	select #use("cols")# from kg_camera where #use("condition")#

cols
===

	id,school_id,classroom_id,device_serial,device_name,model,verify_code

updateSample
===

	`id`=#id#,`school_id`=#schoolId#,`classroom_id`=#classroomId#,`device_serial`=#deviceSerial#,`device_name`=#deviceName#,`model`=#model#,`verify_code`=#verifyCode#

condition
===

	1 = 1
	@if(!isEmpty(schoolId)){
	 and `school_id`=#schoolId#
	@}
	@if(!isEmpty(classroomId)){
	 and `classroom_id`=#classroomId#
	@}
	@if(!isEmpty(deviceSerial)){
	 and `device_serial`=#deviceSerial#
	@}
	@if(!isEmpty(deviceName)){
	 and `device_name`=#deviceName#
	@}
	@if(!isEmpty(model)){
	 and `model`=#model#
	@}
	@if(!isEmpty(verifyCode)){
	 and `verify_code`=#verifyCode#
	@}
