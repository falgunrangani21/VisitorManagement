package com.efive.VisitorManagement.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ContactPersonMaster {

	
	private Long personid;
	private Long locationid;
	private String personname;
	private String designation;
	private String contactnumber;
	private Integer active;
	private Timestamp createdon;
	private String createdby;
	private Timestamp modifiedon;
	private String modifiedby;
	private String ipaddress;
	private String macaddress;
	
	
	
	public ContactPersonMaster() {
			}

	//field constructor
	public ContactPersonMaster(Long personid, Long locationid, String personname, String designation,
			String contactnumber, Integer active, Timestamp createdon, String createdby, Timestamp modifiedon,
			String modifiedby, String ipaddress, String macaddress) {
		super();
		this.personid = personid;
		this.locationid = locationid;
		this.personname = personname;
		this.designation = designation;
		this.contactnumber = contactnumber;
		this.active = active;
		this.createdon = createdon;
		this.createdby = createdby;
		this.modifiedon = modifiedon;
		this.modifiedby = modifiedby;
		this.ipaddress = ipaddress;
		this.macaddress = macaddress;
	}
	

	@Id
	@Column(name = "personid", unique = true, nullable = false, precision = 8, scale = 0)

	public Long getPersonid() {
		return personid;
	}

	

	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	
	@Column(name = "locationid",  nullable = true, precision = 8, scale = 0)
	public Long getLocationid() {
		return locationid;
	}

	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}

	
	@Column(name = "personname", length = 128)
	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}
	@Column(name = "designation", length = 64)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name = "contactnumber", length = 12)
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	@Column(name = "active")
	public Integer getActive() {
		return active;
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
