package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Departmentmaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "departmentmaster", catalog = "visitormanagement")
public class Departmentmaster implements java.io.Serializable {

	// Fields

	private Long departmentid;
	private Long companyid;
	private Long locationid;
	private String deptname;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Departmentmaster() {
	}

	/** minimal constructor */
	public Departmentmaster(Long departmentid, Timestamp createdon,
			Timestamp modifiedon) {
		this.departmentid = departmentid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Departmentmaster(Long departmentid, Long companyid,
			Long locationid, String deptname, Integer active,
			Timestamp createdon, String createdby, Timestamp modifiedon,
			String modifiedby, String ipaddress, String macaddress) {
		this.departmentid = departmentid;
		this.companyid = companyid;
		this.locationid = locationid;
		this.deptname = deptname;
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
	@Column(name = "departmentid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}

	@Column(name = "companyid", precision = 8, scale = 0)
	public Long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	@Column(name = "locationid", precision = 8, scale = 0)
	public Long getLocationid() {
		return this.locationid;
	}

	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}

	@Column(name = "deptname", length = 128)
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
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