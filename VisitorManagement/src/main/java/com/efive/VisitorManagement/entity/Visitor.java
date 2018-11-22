package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Visitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "visitor", catalog = "visitormanagement")
public class Visitor implements java.io.Serializable {

	// Fields

	private Long vid;
	private Long vcompanyid;
	private String vname;
	private String vemail;
	private String vcontactnumber;
	private String vphoto;
	private String visfromcompany;
	private String vlocation;
	private String visitortype;
	private Long companyid;
	private Long locationid;
	private Long departmentid;
	private Long employeeid;
	private String purpose;
	private Long vpassid;
	private Long noofperson;
	private String isvehicle;
	private String vehiclenumber;
	private String ismaterialcarried;
	private String materialdeposit;
	private Timestamp checkedin;
	private Timestamp checkedout;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Visitor() {
	}

	/** minimal constructor */
	public Visitor(Long vid, Timestamp checkedin, Timestamp checkedout,
			Timestamp createdon, Timestamp modifiedon) {
		this.vid = vid;
		this.checkedin = checkedin;
		this.checkedout = checkedout;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Visitor(Long vid, Long vcompanyid, String vname,
			String vemail, String vcontactnumber, String vphoto,
			String visfromcompany, String vlocation, String visitortype,Long companyid,
			Long locationid, Long departmentid, Long employeeid,
			String purpose, Long vpassid, Long noofperson,
			String isvehicle, String vehiclenumber, String ismaterialcarried,
			String materialdeposit, Timestamp checkedin, Timestamp checkedout,
			Integer active, Timestamp createdon, String createdby,
			Timestamp modifiedon, String modifiedby, String ipaddress,
			String macaddress) {
		this.vid = vid;
		this.vcompanyid = vcompanyid;
		this.vname = vname;
		this.vemail = vemail;
		this.vcontactnumber = vcontactnumber;
		this.vphoto = vphoto;
		this.visfromcompany = visfromcompany;
		this.vlocation = vlocation;
		this.visitortype=visitortype;
		this.companyid = companyid;
		this.locationid = locationid;
		this.departmentid = departmentid;
		this.employeeid = employeeid;
		this.purpose = purpose;
		this.vpassid = vpassid;
		this.noofperson = noofperson;
		this.isvehicle = isvehicle;
		this.vehiclenumber = vehiclenumber;
		this.ismaterialcarried = ismaterialcarried;
		this.materialdeposit = materialdeposit;
		this.checkedin = checkedin;
		this.checkedout = checkedout;
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
	@Column(name = "vid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getVid() {
		return this.vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	@Column(name = "vcompanyid", precision = 8, scale = 0)
	public Long getVcompanyid() {
		return this.vcompanyid;
	}

	public void setVcompanyid(Long vcompanyid) {
		this.vcompanyid = vcompanyid;
	}

	@Column(name = "vname", length = 128)
	public String getVname() {
		return this.vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	@Column(name = "vemail", length = 128)
	public String getVemail() {
		return this.vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	@Column(name = "vcontactnumber", length = 12)
	public String getVcontactnumber() {
		return this.vcontactnumber;
	}

	public void setVcontactnumber(String vcontactnumber) {
		this.vcontactnumber = vcontactnumber;
	}

	@Column(name = "vphoto", length = 1024)
	public String getVphoto() {
		return this.vphoto;
	}

	public void setVphoto(String vphoto) {
		this.vphoto = vphoto;
	}

	@Column(name = "visfromcompany", length = 1)
	public String getVisfromcompany() {
		return this.visfromcompany;
	}

	public void setVisfromcompany(String visfromcompany) {
		this.visfromcompany = visfromcompany;
	}

	@Column(name = "vlocation", length = 1024)
	public String getVlocation() {
		return this.vlocation;
	}

	public void setVlocation(String vlocation) {
		this.vlocation = vlocation;
	}
	
	@Column(name = "visitortype", length = 32)
	public String getVisitortype() {
		return visitortype;
	}

	public void setVisitortype(String visitortype) {
		this.visitortype = visitortype;
	}

	@Column(name = "companyid", precision = 8, scale = 0)
	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	@Column(name = "locationid", precision = 8, scale = 0)
	public Long getLocationid() {
		return locationid;
	}

	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}
	
	@Column(name = "departmentid", precision = 8, scale = 0)
	public Long getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}

	@Column(name = "employeeid", precision = 8, scale = 0)
	public Long getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
	}

	@Column(name = "purpose", length = 256)
	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "vpassid", precision = 8, scale = 0)
	public Long getvpassid() {
		return this.vpassid;
	}

	public void setvpassid(Long vpassid) {
		this.vpassid = vpassid;
	}

	@Column(name = "noofperson", precision = 8, scale = 0)
	public Long getNoofperson() {
		return this.noofperson;
	}

	public void setNoofperson(Long noofperson) {
		this.noofperson = noofperson;
	}

	@Column(name = "isvehicle", length = 1)
	public String getIsvehicle() {
		return this.isvehicle;
	}

	public void setIsvehicle(String isvehicle) {
		this.isvehicle = isvehicle;
	}

	@Column(name = "vehiclenumber", length = 16)
	public String getVehiclenumber() {
		return this.vehiclenumber;
	}

	public void setVehiclenumber(String vehiclenumber) {
		this.vehiclenumber = vehiclenumber;
	}

	@Column(name = "ismaterialcarried", length = 1)
	public String getIsmaterialcarried() {
		return this.ismaterialcarried;
	}

	public void setIsmaterialcarried(String ismaterialcarried) {
		this.ismaterialcarried = ismaterialcarried;
	}

	@Column(name = "materialdeposit", length = 64)
	public String getMaterialdeposit() {
		return this.materialdeposit;
	}

	public void setMaterialdeposit(String materialdeposit) {
		this.materialdeposit = materialdeposit;
	}

	@Column(name = "checkedin", nullable = true, length = 19)
	public Timestamp getCheckedin() {
		return this.checkedin;
	}

	public void setCheckedin(Timestamp checkedin) {
		this.checkedin = checkedin;
	}

	@Column(name = "checkedout", nullable = true, length = 19)
	public Timestamp getCheckedout() {
		return this.checkedout;
	}

	public void setCheckedout(Timestamp checkedout) {
		this.checkedout = checkedout;
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