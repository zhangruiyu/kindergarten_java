package main.kotlin.kindergarten.web.entity;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-07-03
*/
public class KgArea   implements Serializable{
	private Integer areaId ;
	private Integer parentId ;
	private String cityName ;
	
	public KgArea() {
	}
	
	public Integer getAreaId(){
		return  areaId;
	}
	public void setAreaId(Integer areaId ){
		this.areaId = areaId;
	}
	
	public Integer getParentId(){
		return  parentId;
	}
	public void setParentId(Integer parentId ){
		this.parentId = parentId;
	}
	
	public String getCityName(){
		return  cityName;
	}
	public void setCityName(String cityName ){
		this.cityName = cityName;
	}
	
	
	

}
