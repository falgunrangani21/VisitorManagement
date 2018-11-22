package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Companymaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "companymaster", catalog = "visitormanagement")
public class Companymaster implements java.io.Serializable {

	// Fields

	private Long companyid;
	private String cname;
	private String cemail;
	private String ctelephonenumber;
	private String workDetails;
	private String cfax;
	private Integer cnoOfemployee;
	private Integer cnoOfbranches;
	private String cwebsite;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;
	
	@Id
	@Column(name = "companyid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	@Column(name = "cname", length = 128)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Column(name = "cemail", length = 128)
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	@Column(name = "ctelephonenumber", length = 12)
	public String getCtelephonenumber() {
		return ctelephonenumber;
	}
	public void setCtelephonenumber(String ctelephonenumber) {
		this.ctelephonenumber = ctelephonenumber;
	}
	@Column(name = "workDetails", length = 512)
	public String getWorkDetails() {
		return workDetails;
	}
	public void setWorkDetails(String workDetails) {
		this.workDetails = workDetails;
	}
	@Column(name = "cfax", length = 10)
	public String getCfax() {
		return cfax;
	}
	public void setCfax(String cfax) {
		this.cfax = cfax;
	}
	@Column(name = "cnoOfemployee", precision = 8, scale = 0)
	public Integer getCnoOfemployee() {
		return cnoOfemployee;
	}

	public void setCnoOfemployee(Integer cnoOfemployee) {
		this.cnoOfemployee = cnoOfemployee;
	}
	
	@Column(name = "cnoOfbranches", precision = 8, scale = 0)
	public Integer getCnoOfbranches() {
		return cnoOfbranches;
	}
	public void setCnoOfbranches(Integer cnoOfbranches) {
		this.cnoOfbranches = cnoOfbranches;
	}
	@Column(name = "cwebsite", length = 128)
	public String getCwebsite() {
		return cwebsite;
	}
	public void setCwebsite(String cwebsite) {
		this.cwebsite = cwebsite;
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