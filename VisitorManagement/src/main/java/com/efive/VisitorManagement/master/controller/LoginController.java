package com.efive.VisitorManagement.master.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@GetMapping(value= {"/", "login"})
	public String loginPage()
	{
		return "login/login";
	}
	
	@RequestMapping(value="/logout",method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) {
		System.out.println("in logout");
		try{ 
			HttpSession session=request.getSession(false);
			
			if(session!=null) {
				session.invalidate();
			}
			 		}catch(Exception ex){ 
			ex.printStackTrace();
		}
			return "login/login";
	}
 
}
