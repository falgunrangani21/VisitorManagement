package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usermaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usermaster", catalog = "visitormanagement")
public class Usermaster implements java.io.Serializable {

	// Fields
	
	private Long userid;
	private String username;
	private String loginname;
	private String usertype;
	private String roleaccesstype;
	private String gender;
	private String password;
	private String email;
	private String contactnumber;
	private Long countryid;
	private Long stateid;
	private Long cityid;
	private Timestamp passwordupdateon;
	private Long isfirst;
	private Timestamp lastLogin;
	private String profileurl;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;
	
	@Id
	@Column(name = "userid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Column(name = "username", length = 128)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "loginname", length = 128)
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	@Column(name = "usertype", length = 1)
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	@Column(name = "roleaccesstype", length = 1)
	public String getRoleaccesstype() {
		return roleaccesstype;
	}
	public void setRoleaccesstype(String roleaccesstype) {
		this.roleaccesstype = roleaccesstype;
	}
	
	@Column(name = "gender", length = 1)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "password", length = 16)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "email", length = 128)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "contactnumber", length = 12)
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	@Column(name = "countryid", precision = 1, scale = 0)
	public Long getCountryid() {
		return countryid;
	}
	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}
	@Column(name = "stateid", precision = 1, scale = 0)
	public Long getStateid() {
		return stateid;
	}
	public void setStateid(Long stateid) {
		this.stateid = stateid;
	}
	@Column(name = "cityid", precision = 1, scale = 0)
	public Long getCityid() {
		return cityid;
	}
	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}
	@Column(name = "passwordupdateon", nullable = true, length = 19)
	public Timestamp getPasswordupdateon() {
		return passwordupdateon;
	}
	public void setPasswordupdateon(Timestamp passwordupdateon) {
		this.passwordupdateon = passwordupdateon;
	}
	@Column(name = "isfirst", precision = 1, scale = 0)
	public Long getIsfirst() {
		return isfirst;
	}
	public void setIsfirst(Long isfirst) {
		this.isfirst = isfirst;
	}
	@Column(name = "lastLogin", nullable = true, length = 19)
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	@Column(name = "active")
	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "createdon", nullable = true, length = 19)
	public Timestamp getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	@Column(name = "createdby", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "modifiedon", nullable = true, length = 19)
	public Timestamp getModifiedon() {
		return this.modifiedon;
	}

	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}

	@Column(name = "modifiedby", length = 32)
	public String getModifiedby() {
		return this.modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	@Column(name = "ipaddress", length = 16)
	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	@Column(name = "macaddress", length = 32)
	public String getMacaddress() {
		return this.macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
	@Column(name = "profileurl", nullable = true, length = 1024)
	public String getProfileurl() {
		return profileurl;
	}
	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}


	

}