package com.UserContentMicroservice.UserContent;


import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthUtil {

	public static final long EXP_IN_SECONDS = 60L;

	@Value("${token.secret}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(claims, userName);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = Date.from(Instant.now());
		final Date expirationDate = calculateExpirationDate(createdDate);
		return Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).setSubject(subject)
				.setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	private Date calculateExpirationDate(Date createdDate) {
		
		return Date.from(Instant.ofEpochMilli(createdDate.getTime() + EXP_IN_SECONDS * 1000L));
	}

}
