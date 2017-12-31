package kindergarten.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/*
 *
 * gen by beetlsql 2017-07-03
 */
public class KgSchool implements Serializable {
    private Integer id;
    @JsonIgnore
    private Integer areaId;
    @JsonIgnore
    private Integer childrenCount;
    @JsonIgnore
    private Integer parentId;
    @JsonIgnore
    private Integer shcoolState;
    //文字地址
    private String address;
    @JsonIgnore
    private String areaName;
    @JsonIgnore
    private String details;
    @JsonIgnore
    private String ipAddress;
    //园长真实姓名
    private String leaderName;
    private String schoolName;
    private String shcoolPicture;
    //招生热线
    private String tel;
    @JsonIgnore
    private Date addtime;
    //幼儿园创建时间
    @JsonIgnore
    private Date createtime;

    public KgSchool() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getShcoolState() {
        return shcoolState;
    }

    public void setShcoolState(Integer shcoolState) {
        this.shcoolState = shcoolState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getShcoolPicture() {
        return shcoolPicture;
    }

    public void setShcoolPicture(String shcoolPicture) {
        this.shcoolPicture = shcoolPicture;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


}
