package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Employeemaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employeemaster", catalog = "visitormanagement")
public class Employeemaster implements java.io.Serializable {

	// Fields

	private Long employeeid;
	private Long companyid;
	private Long locationid;
	private String empname;
	private String empgender;
	private String empEmail;
	private String empcontactnumber;
	private String empqualification;
	private String empphoto;
	private Long departmentid;
	private String designation;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Employeemaster() {
	}

	/** minimal constructor */
	public Employeemaster(Long employeeid, Timestamp createdon,
			Timestamp modifiedon) {
		this.employeeid = employeeid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Employeemaster(Long employeeid, Long companyid,
			Long locationid, String empname, String empgender,
			String empEmail, String empcontactnumber, String empqualification,
			String empphoto, Long departmentid, String designation,
			Integer active, Timestamp createdon, String createdby,
			Timestamp modifiedon, String modifiedby, String ipaddress,
			String macaddress) {
		this.employeeid = employeeid;
		this.companyid = companyid;
		this.locationid = locationid;
		this.empname = empname;
		this.empgender = empgender;
		this.empEmail = empEmail;
		this.empcontactnumber = empcontactnumber;
		this.empqualification = empqualification;
		this.empphoto = empphoto;
		this.departmentid = departmentid;
		this.designation = designation;
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
	@Column(name = "employeeid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
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

	@Column(name = "empname", length = 128)
	public String getEmpname() {
		return this.empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	@Column(name = "empgender", length = 1)
	public String getEmpgender() {
		return this.empgender;
	}

	public void setEmpgender(String empgender) {
		this.empgender = empgender;
	}

	@Column(name = "empEmail", length = 128)
	public String getEmpEmail() {
		return this.empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	@Column(name = "empcontactnumber", length = 12)
	public String getEmpcontactnumber() {
		return this.empcontactnumber;
	}

	public void setEmpcontactnumber(String empcontactnumber) {
		this.empcontactnumber = empcontactnumber;
	}

	@Column(name = "empqualification", length = 32)
	public String getEmpqualification() {
		return this.empqualification;
	}

	public void setEmpqualification(String empqualification) {
		this.empqualification = empqualification;
	}

	@Column(name = "empphoto", length = 1024)
	public String getEmpphoto() {
		return this.empphoto;
	}

	public void setEmpphoto(String empphoto) {
		this.empphoto = empphoto;
	}

	@Column(name = "departmentid", precision = 8, scale = 0)
	public Long getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}

	@Column(name = "designation", length = 64)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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