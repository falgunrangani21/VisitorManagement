package com.efive.VisitorManagement.master.bean;

public class RestResponseInAjax {

	private String status;
	private Object data;

	public RestResponseInAjax() {

	}

	public RestResponseInAjax(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
