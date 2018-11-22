package com.efive.VisitorManagement.master.bean;

import java.util.List;
import com.efive.VisitorManagement.common.EfiveDatatTableBean;

public class CityMasterBean extends EfiveDatatTableBean {

	public static String[] cols = { "cityid", "cityname", "stdcode", "pincode", "active" };
	public List<?> getCityList;

	public List<?> getGetCityList() {
		return getCityList;
	}

	public void setGetCityList(List<?> getCityList) {
		this.getCityList = getCityList;
	}

}
