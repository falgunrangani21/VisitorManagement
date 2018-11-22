package com.efive.VisitorManagement.master.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efive.VisitorManagement.entity.Usermaster;
import com.efive.VisitorManagement.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	private JwtGenerator jwtgenerator;

	public TokenController(JwtGenerator jwtgenerator) {

		this.jwtgenerator = jwtgenerator;
	}

	@GetMapping
	public String generated(@RequestBody final Usermaster usermaster) {
		return jwtgenerator.generate(usermaster);
	}
}
