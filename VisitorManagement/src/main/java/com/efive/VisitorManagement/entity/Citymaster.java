package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Citymaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "citymaster", catalog = "visitormanagement")
public class Citymaster implements java.io.Serializable {

	// Fields

	private Long cityid;
	private Long countryid;
	private Long stateid;
	private String cityname;
	private String stdcode;
	private String pincode;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Citymaster() {
	}

	/** minimal constructor */
	public Citymaster(Long cityid, Timestamp createdon, Timestamp modifiedon) {
		this.cityid = cityid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Citymaster(Long cityid, Long countryid, Long stateid,
			String cityname, String stdcode, String pincode, Integer active,
			Timestamp createdon, String createdby, Timestamp modifiedon,
			String modifiedby, String ipaddress, String macaddress) {
		this.cityid = cityid;
		this.countryid = countryid;
		this.stateid = stateid;
		this.cityname = cityname;
		this.stdcode = stdcode;
		this.pincode = pincode;
		this.active = active;
		this.createdon = createdon;
		this.createdby = createdby;
		this.modifiedon = modifiedon;
		this.modifiedby = modifiedby;
		this.ipaddress = ipaddress;
		this.macaddress = macaddress;
	}

	// Property accessors
	@Id
	@Column(name = "cityid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getCityid() {
		return this.cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	@Column(name = "countryid", precision = 8, scale = 0)
	public Long getCountryid() {
		return this.countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	@Column(name = "stateid", precision = 8, scale = 0)
	public Long getStateid() {
		return this.stateid;
	}

	public void setStateid(Long stateid) {
		this.stateid = stateid;
	}

	@Column(name = "cityname", length = 64)
	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@Column(name = "stdcode", length = 8)
	public String getStdcode() {
		return this.stdcode;
	}

	public void setStdcode(String stdcode) {
		this.stdcode = stdcode;
	}

	@Column(name = "pincode", length = 16)
	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

}