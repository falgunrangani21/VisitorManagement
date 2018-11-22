package com.efive.VisitorManagement.master.bean;

import java.sql.Timestamp;

public class ContactPersonBean {

	private Long[] personid;
	private Long[] locationmaster;
	private String[] personname;
	private String[] designation;
	private String[] contactnumber;
	private Integer[] active;
	private Timestamp[] createdon;
	private String[] createdby;
	private Timestamp[] modifiedon;
	private String[] modifiedby;
	private String[] ipaddress;
	private String[] macaddress;

	public Long[] getPersonid() {
		return personid;
	}

	public void setPersonid(Long[] personid) {
		this.personid = personid;
	}

	public Long[] getLocationmaster() {
		return locationmaster;
	}

	public void setLocationmaster(Long[] locationmaster) {
		this.locationmaster = locationmaster;
	}

	public String[] getPersonname() {
		return personname;
	}

	public void setPersonname(String[] personname) {
		this.personname = personname;
	}

	public String[] getDesignation() {
		return designation;
	}

	public void setDesignation(String[] designation) {
		this.designation = designation;
	}

	public String[] getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String[] contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Integer[] getActive() {
		return active;
	}

	public void setActive(Integer[] active) {
		this.active = active;
	}

	public Timestamp[] getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Timestamp[] createdon) {
		this.createdon = createdon;
	}

	public String[] getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String[] createdby) {
		this.createdby = createdby;
	}

	public Timestamp[] getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Timestamp[] modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String[] getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String[] modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String[] getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String[] ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String[] getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String[] macaddress) {
		this.macaddress = macaddress;
	}

}
