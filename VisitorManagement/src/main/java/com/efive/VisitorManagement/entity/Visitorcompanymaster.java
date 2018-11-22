package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Visitorcompanymaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "visitorcompanymaster", catalog = "visitormanagement")
public class Visitorcompanymaster implements java.io.Serializable {

	// Fields

	private Long vcompanyid;
	private String vcname;
	private String vcemail;
	private String vccontactno;
	private Long vccountryid;
	private Long vcstateid;
	private Long vccityid;
	private String vcaddress;
	private String vcfax;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Visitorcompanymaster() {
	}

	/** minimal constructor */
	public Visitorcompanymaster(Long vcompanyid, Timestamp createdon,
			Timestamp modifiedon) {
		this.vcompanyid = vcompanyid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Visitorcompanymaster(Long vcompanyid, String vcname,
			String vcemail, String vccontactno, Long vccountryid,
			Long vcstateid, Long vccityid, String vcaddress,
			String vcfax, Integer active, Timestamp createdon,
			String createdby, Timestamp modifiedon, String modifiedby,
			String ipaddress, String macaddress) {
		this.vcompanyid = vcompanyid;
		this.vcname = vcname;
		this.vcemail = vcemail;
		this.vccontactno = vccontactno;
		this.vccountryid = vccountryid;
		this.vcstateid = vcstateid;
		this.vccityid = vccityid;
		this.vcaddress = vcaddress;
		this.vcfax = vcfax;
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
	@Column(name = "vcompanyid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getVcompanyid() {
		return this.vcompanyid;
	}

	public void setVcompanyid(Long vcompanyid) {
		this.vcompanyid = vcompanyid;
	}

	@Column(name = "vcname", length = 128)
	public String getVcname() {
		return this.vcname;
	}

	public void setVcname(String vcname) {
		this.vcname = vcname;
	}

	@Column(name = "vcemail", length = 128)
	public String getVcemail() {
		return this.vcemail;
	}

	public void setVcemail(String vcemail) {
		this.vcemail = vcemail;
	}

	@Column(name = "vccontactno", length = 12)
	public String getVccontactno() {
		return this.vccontactno;
	}

	public void setVccontactno(String vccontactno) {
		this.vccontactno = vccontactno;
	}

	@Column(name = "vccountryid", precision = 8, scale = 0)
	public Long getVccountryid() {
		return this.vccountryid;
	}

	public void setVccountryid(Long vccountryid) {
		this.vccountryid = vccountryid;
	}

	@Column(name = "vcstateid", precision = 8, scale = 0)
	public Long getVcstateid() {
		return this.vcstateid;
	}

	public void setVcstateid(Long vcstateid) {
		this.vcstateid = vcstateid;
	}

	@Column(name = "vccityid", precision = 8, scale = 0)
	public Long getVccityid() {
		return this.vccityid;
	}

	public void setVccityid(Long vccityid) {
		this.vccityid = vccityid;
	}

	@Column(name = "vcaddress", length = 512)
	public String getVcaddress() {
		return this.vcaddress;
	}

	public void setVcaddress(String vcaddress) {
		this.vcaddress = vcaddress;
	}

	@Column(name = "vcfax", length = 10)
	public String getVcfax() {
		return this.vcfax;
	}

	public void setVcfax(String vcfax) {
		this.vcfax = vcfax;
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