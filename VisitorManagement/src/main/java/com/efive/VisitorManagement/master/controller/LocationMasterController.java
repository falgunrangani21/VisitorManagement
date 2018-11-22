package com.efive.VisitorManagement.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.efive.VisitorManagement.master.service.CommonUtilService;

@Controller
public class LocationMasterController {

	@Autowired
	private CommonUtilService service;

	@GetMapping("/locationmaster")
	public String locationMasterPage(Model model) {

		model.addAttribute("countryList", service.getCountryData(null));
		model.addAttribute("companyList", service.getCompanyData(null));

		return "master/location_master";
	}
}
