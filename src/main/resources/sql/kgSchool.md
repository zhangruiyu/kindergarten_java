sample
===
* 注释

	select #use("cols")# from kg_school where #use("condition")#

cols
===

	id,school_name,area_id,parent_id,area_name,tel,children_count,leader_name,address,ip_address,details,shcool_picture,shcool_state,createTime,addTime

updateSample
===

	`id`=#id#,`school_name`=#schoolName#,`area_id`=#areaId#,`parent_id`=#parentId#,`area_name`=#areaName#,`tel`=#tel#,`children_count`=#childrenCount#,`leader_name`=#leaderName#,`address`=#address#,`ip_address`=#ipAddress#,`details`=#details#,`shcool_picture`=#shcoolPicture#,`shcool_state`=#shcoolState#,`createTime`=#createtime#,`addTime`=#addtime#

condition
===

	1 = 1  
	@if(!isEmpty(schoolName)){
	 and `school_name`=#schoolName#
	@}
	@if(!isEmpty(areaId)){
	 and `area_id`=#areaId#
	@}
	@if(!isEmpty(parentId)){
	 and `parent_id`=#parentId#
	@}
	@if(!isEmpty(areaName)){
	 and `area_name`=#areaName#
	@}
	@if(!isEmpty(tel)){
	 and `tel`=#tel#
	@}
	@if(!isEmpty(childrenCount)){
	 and `children_count`=#childrenCount#
	@}
	@if(!isEmpty(leaderName)){
	 and `leader_name`=#leaderName#
	@}
	@if(!isEmpty(address)){
	 and `address`=#address#
	@}
	@if(!isEmpty(ipAddress)){
	 and `ip_address`=#ipAddress#
	@}
	@if(!isEmpty(details)){
	 and `details`=#details#
	@}
	@if(!isEmpty(shcoolPicture)){
	 and `shcool_picture`=#shcoolPicture#
	@}
	@if(!isEmpty(shcoolState)){
	 and `shcool_state`=#shcoolState#
	@}
	@if(!isEmpty(createtime)){
	 and `createTime`=#createtime#
	@}
	@if(!isEmpty(addtime)){
	 and `addTime`=#addtime#
	@}
	
