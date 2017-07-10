sample
===
* 注释

	select #use("cols")# from kg_area where #use("condition")#

cols
===

	area_id,parent_id,city_name

updateSample
===

	`area_id`=#areaId#,`parent_id`=#parentId#,`city_name`=#cityName#

condition
===

	1 = 1  
	@if(!isEmpty(areaId)){
	 and `area_id`=#areaId#
	@}
	@if(!isEmpty(parentId)){
	 and `parent_id`=#parentId#
	@}
	@if(!isEmpty(cityName)){
	 and `city_name`=#cityName#
	@}
	
