package com.efive.VisitorManagement.master.bean;

import java.util.List;

import com.efive.VisitorManagement.common.EfiveDatatTableBean;

public class CountryMasterBean extends EfiveDatatTableBean {

	public static String[] cols = { "countryname", "isdcode" };

	public List<?> getCountryList;

	public List<?> getGetCountryList() {
		return getCountryList;
	}

	public void setGetCountryList(List<?> getCountryList) {
		this.getCountryList = getCountryList;
	}
}