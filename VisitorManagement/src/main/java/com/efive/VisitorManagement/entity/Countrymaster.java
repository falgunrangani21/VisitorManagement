package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Countrymaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "countrymaster", catalog = "visitormanagement")
public class Countrymaster implements java.io.Serializable {

	// Fields

	private Long countryid;
	private String countryname;
	private String isdcode;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Countrymaster() {
	}

	/** minimal constructor */
	public Countrymaster(Long countryid, Timestamp createdon,
			Timestamp modifiedon) {
		this.countryid = countryid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Countrymaster(Long countryid, String countryname, String isdcode,
			Integer active, Timestamp createdon, String createdby,
			Timestamp modifiedon, String modifiedby, String ipaddress,
			String macaddress) {
		this.countryid = countryid;
		this.countryname = countryname;
		this.isdcode = isdcode;
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
	@Column(name = "countryid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getCountryid() {
		return this.countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	@Column(name = "countryname", length = 64)
	public String getCountryname() {
		return this.countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	@Column(name = "isdcode", length = 8)
	public String getIsdcode() {
		return this.isdcode;
	}

	public void setIsdcode(String isdcode) {
		this.isdcode = isdcode;
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