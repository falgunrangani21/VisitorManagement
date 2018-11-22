package com.efive.VisitorManagement.security;

import org.springframework.stereotype.Component;

import com.efive.VisitorManagement.entity.Usermaster; 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private String secret = "visitor";

	public Usermaster validate(String token) {

		Usermaster jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			jwtUser = new Usermaster();

			jwtUser.setLoginname(body.getSubject());
			jwtUser.setUserid(Long.parseLong((String) body.get("userid")));
			jwtUser.setUsertype((String) body.get("usertype"));
		} catch (Exception e) {
			System.out.println(e);
		}

		return jwtUser;
	}

}
