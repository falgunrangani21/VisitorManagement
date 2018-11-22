package com.efive.VisitorManagement.master.bean;

import java.util.List;

import com.efive.VisitorManagement.common.EfiveDatatTableBean;

public class StateMasterBean extends EfiveDatatTableBean {

	public static String[] cols = { "statename" };

	public List<?> getStateList;

	public List<?> getGetStateList() {
		return getStateList;
	}

	public void setGetStateList(List<?> getStateList) {
		this.getStateList = getStateList;
	}

}
