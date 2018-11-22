package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Locationmaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "locationmaster", catalog = "visitormanagement")
public class Locationmaster implements java.io.Serializable {

	// Fields

	private Long locationid;
	private Long companyid;
	private String branchname;
	private String address;
	private Long countryid;
	private Long stateid;
	private Long cityid;
	private String email;
	private String contactno;
	private String fax;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Locationmaster() {
	}

	/** minimal constructor */
	public Locationmaster(Long locationid, Timestamp createdon,
			Timestamp modifiedon) {
		this.locationid = locationid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	

	// Property accessors
	@Id
	@Column(name = "locationid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getLocationid() {
		return this.locationid;
	}

	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}

	
	@Column(name = "companyid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	@Column(name = "branchname", length = 128)
	public String getBranchname() {
		return this.branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	@Column(name = "address", length = 512)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Column(name = "cityid", precision = 8, scale = 0)
	public Long getCityid() {
		return this.cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contactno", length = 12)
	public String getContactno() {
		return this.contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	@Column(name = "fax", length = 10)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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