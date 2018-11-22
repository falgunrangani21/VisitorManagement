package com.efive.VisitorManagement.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CountryMasterController {

	@RequestMapping(value = "/countrymaster")
	public String searchCountryMaster() {
		return "master/country_master";
	}
}
