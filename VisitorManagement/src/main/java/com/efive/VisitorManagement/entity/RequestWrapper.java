package com.efive.VisitorManagement.entity;

public class RequestWrapper {
	private ContactPersonMaster[] personmaster;
	private Locationmaster locationmaster;
	public ContactPersonMaster[] getPersonmaster() {
		return personmaster;
	}
	public void setPersonmaster(ContactPersonMaster[] personmaster) {
		this.personmaster = personmaster;
	}
	public Locationmaster getLocationmaster() {
		return locationmaster;
	}
	public void setLocationmaster(Locationmaster locationmaster) {
		this.locationmaster = locationmaster;
	}
	

}
