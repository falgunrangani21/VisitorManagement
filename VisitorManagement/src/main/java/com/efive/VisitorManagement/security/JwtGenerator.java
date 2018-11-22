package com.efive.VisitorManagement.security;

import org.springframework.stereotype.Component;

import com.efive.VisitorManagement.entity.Usermaster;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	public String generate(Usermaster jwtUSer) {

		Claims claims = Jwts.claims().setSubject(jwtUSer.getLoginname());
		claims.put("userid", String.valueOf(jwtUSer.getUserid()));
		claims.put("usertype", jwtUSer.getUsertype());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "visitor").compact();
	}
}