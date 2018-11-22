package com.efive.VisitorManagement.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.efive.VisitorManagement.master.service.CommonUtilService;

@Controller
public class EmployeeMasterController{	

	@Autowired
	private CommonUtilService service;
	
	@RequestMapping("/employeemaster")
	public String searchEmployeeMaster(Model model) {
		model.addAttribute("companyList", service.getCompanyData(null));
		return "master/employee_master";
	}
}
