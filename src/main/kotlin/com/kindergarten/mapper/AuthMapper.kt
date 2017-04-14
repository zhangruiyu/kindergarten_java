package com.kindergarten.mapper

import com.kindergarten.entity.Profile
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * Created by zhangruiyu on 2017/4/9.
 */
@Mapper
interface AuthMapper {
    @Select("SELECT * FROM t_profile WHERE profile_ID = #{profile_ID}")
    fun getById(@Param("profile_ID") profile_ID: Int): Profile
    @Insert("Insert int ")
    fun rigister():Int
}