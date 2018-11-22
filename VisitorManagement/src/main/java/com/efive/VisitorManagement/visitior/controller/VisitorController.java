package com.efive.VisitorManagement.visitior.controller;


import com.efive.VisitorManagement.master.service.CommonUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VisitorController {

	@Autowired
	private CommonUtilService service;
	@Value("${visitor.serverpath}")
	String serverpath;
	
	@RequestMapping(value="/visitor",method=RequestMethod.GET)
	public String visitor(Model model) {
		model.addAttribute("serverpath", serverpath);
		model.addAttribute("countryList", service.getCountryData(null));
		model.addAttribute("companyList", service.getCompanyData(null));
		model.addAttribute("employeeList", service.getEmployeeData(null, null, null));
		model.addAttribute("vcompanyList", service.getVisitorCompanyData(null));
		model.addAttribute("vlocationList", service.getVisitorLocation(null));
		model.addAttribute("vcardList", service.getVisitorCard(null));
		model.addAttribute("currentVisitorList", service.getCurrentVisitorByDate());
		return "visitor/visitor";
	}
}
