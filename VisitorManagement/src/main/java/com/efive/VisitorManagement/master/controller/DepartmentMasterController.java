package com.efive.VisitorManagement.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.efive.VisitorManagement.master.service.CommonUtilService;

@Controller
public class DepartmentMasterController {

	@Autowired
	private CommonUtilService service;

	@RequestMapping("/departmentmaster")
	public String CompanyMasterPage(Model model) {
		model.addAttribute("companyList", service.getCompanyData(null));
		return "master/department_master";
	}
}
