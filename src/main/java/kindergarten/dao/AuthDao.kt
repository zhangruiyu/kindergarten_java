package kindergarten.dao

//package com.kindergarten.dao
//
//import com.kindergarten.entity.Profile
//import org.apache.ibatis.annotations.Insert
//import org.apache.ibatis.annotations.Mapper
//import org.apache.ibatis.annotations.Param
//import org.apache.ibatis.annotations.Select
//
///**
// * Created by zhangruiyu on 2017/4/9.
// */
//@Mapper
//interface AuthDao {
//    @Select("SELECT * FROM t_profile WHERE profile_ID = #{profile_ID}")
//    fun getById(@Param("profile_ID") profile_ID: Int): Profile
//
//    @Insert("Insert into t_passport(tel,login_password_hash,register_IP,last_login_time,last_login_IP,wx_open_id) values (#{tel},#{login_password_hash},#{register_IP},#{last_login_time},#{last_login_IP},#{wx_open_id}) ")
//    fun rigister(@Param("tel") tel: String, @Param("login_password_hash") login_password_hash: String, @Param("register_IP") register_IP: String, @Param("last_login_time") last_login_time: String, @Param("last_login_IP") last_login_IP: String, @Param("wx_open_id") wx_open_id: String): Int
//}