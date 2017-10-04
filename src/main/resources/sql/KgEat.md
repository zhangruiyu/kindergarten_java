getEatList
===
* 注释

    SELECT *
    FROM kg_eat
    WHERE school_id =  #school_id# AND YEARWEEK(date_format(create_time, '%Y-%m-%d')) = YEARWEEK(now()) - #index#;

cols
===

	id,school_id,breakfast,lunch,supper,create_time

updateSample
===

	`id`=#id#,`school_id`=#schoolId#,`breakfast`=#breakfast#,`lunch`=#lunch#,`supper`=#supper#,`create_time`=#createTime#

condition
===

	1 = 1
	@if(!isEmpty(schoolId)){
	 and `school_id`=#schoolId#
	@}
	@if(!isEmpty(breakfast)){
	 and `breakfast`=#breakfast#
	@}
	@if(!isEmpty(lunch)){
	 and `lunch`=#lunch#
	@}
	@if(!isEmpty(supper)){
	 and `supper`=#supper#
	@}
	@if(!isEmpty(createTime)){
	 and `create_time`=#createTime#
	@}
