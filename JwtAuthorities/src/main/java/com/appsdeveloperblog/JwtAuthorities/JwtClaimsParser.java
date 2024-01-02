package com.appsdeveloperblog.JwtAuthorities;

import java.util.Base64;
import java.util.Collection;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtClaimsParser {
	
	Jwt<?,?> jwtObject;

	public JwtClaimsParser(String jwt, String secretToken) {
		this.jwtObject = parseJwt(jwt, secretToken);
	}
	
	
	Jwt<?,?> parseJwt(String jwtString, String secretToken) {
		byte[] secretKeyBytes = Base64.getEncoder().encode(secretToken.getBytes());
		SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
		
		JwtParser jwtParser = Jwts.parser()
	                .verifyWith(secretKey)
	                .build();
		
		return jwtParser.parse(jwtString);
	}
	
	public Collection<? extends GrantedAuthority> getUserAuthorities() {
 
		Collection<Map<String, String>> scopes = ((Claims)jwtObject.getPayload()).get("scope", List.class);
	    
	    return scopes.stream()
	    		.map(scopeMap -> new SimpleGrantedAuthority(scopeMap.get("authority")))
	    		.collect(Collectors.toList());
	}
	
	public String getJwtSubject() {
		return ((Claims)jwtObject.getPayload()).getSubject();
	}

}