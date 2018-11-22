package com.efive.VisitorManagement.visitor.bean;

import com.efive.VisitorManagement.common.EfiveDatatTableBean;

public class VisitorBean extends EfiveDatatTableBean{

	public static String[] cols = {"vid","vphoto","vname","vemail","vcontactnumber","visitortype","empname"};

	public String avvisitorid;
	public String isFromCopmany_Searching;
	public String avcompanyid;
	public String avvisitortypeid;
	public String avlocationid;
	public String avemployeeid;
	public String avcardid;
	public String avNoOfPerson;
	 
	public String getAvvisitorid() {
		return avvisitorid;
	}
	public void setAvvisitorid(String avvisitorid) {
		this.avvisitorid = avvisitorid;
	}
	public String getIsFromCopmany_Searching() {
		return isFromCopmany_Searching;
	}
	public void setIsFromCopmany_Searching(String isFromCopmany_Searching) {
		this.isFromCopmany_Searching = isFromCopmany_Searching;
	}
	public String getAvcompanyid() {
		return avcompanyid;
	}
	public void setAvcompanyid(String avcompanyid) {
		this.avcompanyid = avcompanyid;
	}
	public String getAvvisitortypeid() {
		return avvisitortypeid;
	}
	public void setAvvisitortypeid(String avvisitortypeid) {
		this.avvisitortypeid = avvisitortypeid;
	}
	public String getAvlocationid() {
		return avlocationid;
	}
	public void setAvlocationid(String avlocationid) {
		this.avlocationid = avlocationid;
	}
	public String getAvemployeeid() {
		return avemployeeid;
	}
	public void setAvemployeeid(String avemployeeid) {
		this.avemployeeid = avemployeeid;
	}
	public String getAvcardid() {
		return avcardid;
	}
	public void setAvcardid(String avcardid) {
		this.avcardid = avcardid;
	}
	public String getAvNoOfPerson() {
		return avNoOfPerson;
	}
	public void setAvNoOfPerson(String avNoOfPerson) {
		this.avNoOfPerson = avNoOfPerson;
	}
	
	
	
	
}
