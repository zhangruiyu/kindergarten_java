package main.kotlin.kindergarten.web.entity;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-07-03
*/
public class KgClassroom   implements Serializable{
	private Integer id ;
	private Integer chirldCount ;
	private Integer schoolId ;
	private String classroomImage ;
	private String title ;
	
	public KgClassroom() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getChirldCount(){
		return  chirldCount;
	}
	public void setChirldCount(Integer chirldCount ){
		this.chirldCount = chirldCount;
	}
	
	public Integer getSchoolId(){
		return  schoolId;
	}
	public void setSchoolId(Integer schoolId ){
		this.schoolId = schoolId;
	}
	
	public String getClassroomImage(){
		return  classroomImage;
	}
	public void setClassroomImage(String classroomImage ){
		this.classroomImage = classroomImage;
	}
	
	public String getTitle(){
		return  title;
	}
	public void setTitle(String title ){
		this.title = title;
	}
	
	
	

}
