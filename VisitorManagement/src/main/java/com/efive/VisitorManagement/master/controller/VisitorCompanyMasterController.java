package com.efive.VisitorManagement.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.efive.VisitorManagement.master.service.CommonUtilService;

@Controller
public class VisitorCompanyMasterController {

	@Autowired
	private CommonUtilService service;

	@RequestMapping("/visitorcompanymaster")
	public String searchVisitorCompanyMaster(Model model) {
		model.addAttribute("countryList", service.getCountryData(null));
		return "master/visitor_company";
	}
}