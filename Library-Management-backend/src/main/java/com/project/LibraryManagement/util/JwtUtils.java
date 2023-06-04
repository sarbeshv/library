package com.project.LibraryManagement.util;

import org.springframework.stereotype.Component;
import java.util.*;

import com.project.LibraryManagement.Exception.AccessDeniedException;
import com.project.LibraryManagement.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String secret = "This is secret";
	public String generateJwt(Users user) {
	    long milliTime = System.currentTimeMillis();
	    long OneHourInMills = 1 * 60 * 60 * 1000;
	    long ExpireTime = milliTime + OneHourInMills;
		Date issueAt = new Date(milliTime);
		Date ExpireAt = new Date (ExpireTime);
		
		// claims
		Claims claims = Jwts.claims()
				.setIssuer(user.getUserId().toString())
				.setIssuedAt(issueAt)
				.setExpiration(ExpireAt);
		
		
		claims.put("name", user.getUserName());
		claims.put("email", user.getEmail());
		// generate token using claims
		
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
	}
	
	public void verify(String authorization) throws Exception {
	
		try {
			  Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();

		}
		catch(Exception e) {
			throw new AccessDeniedException("Access Denied");
		}
		}
}
