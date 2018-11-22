package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Statemaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "statemaster", catalog = "visitormanagement")
public class Statemaster implements java.io.Serializable {

	// Fields

	private Long stateid;
	private Long countryid;
	private String statename;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;

	// Constructors

	/** default constructor */
	public Statemaster() {
	}

	/** minimal constructor */
	public Statemaster(Long stateid, Timestamp createdon,
			Timestamp modifiedon) {
		this.stateid = stateid;
		this.createdon = createdon;
		this.modifiedon = modifiedon;
	}

	/** full constructor */
	public Statemaster(Long stateid, Long countryid, String statename,
			Integer active, Timestamp createdon, String createdby,
			Timestamp modifiedon, String modifiedby, String ipaddress,
			String macaddress) {
		this.stateid = stateid;
		this.countryid = countryid;
		this.statename = statename;
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
	@Column(name = "stateid", unique = true, nullable = false, precision = 8, scale = 0)
	public Long getStateid() {
		return this.stateid;
	}

	public void setStateid(Long stateid) {
		this.stateid = stateid;
	}

	@Column(name = "countryid", precision = 8, scale = 0)
	public Long getCountryid() {
		return this.countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	@Column(name = "statename", length = 64)
	public String getStatename() {
		return this.statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
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