package com.efive.VisitorManagement.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyMasterController {


	@RequestMapping("/companymaster")
	public String CompanyMasterPage()
	{	
		return "master/company_master";
	}
}
