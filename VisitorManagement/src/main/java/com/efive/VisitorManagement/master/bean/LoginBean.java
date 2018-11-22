package com.efive.VisitorManagement.master.bean;

import java.util.List;

import javax.servlet.http.HttpSession;

public class LoginBean {

	private Long id;
	private String loginname;
	private String password;

    HttpSession session = null;

	public String strQuery;
	public List<?> menuList;
	public List<?> optionList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public List<?> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<?> menuList) {
		this.menuList = menuList;
	}

	public List<?> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<?> optionList) {
		this.optionList = optionList;
	}

}
