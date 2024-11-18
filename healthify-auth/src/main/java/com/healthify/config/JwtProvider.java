package com.healthify.config;

import java.util.Date;
import java.util.stream.Collectors;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationms}")
	private int jwtExpiration;
	private static final String AUTHORITIES_KEY = "auth";

	public String generateJwtToken(Authentication authentication) {

		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().requireSubject(jwtSecret).build().parseSignedClaims(authToken).getPayload();
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature -> Message: {0} ", e);
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token -> Message: {0}", e);
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token -> Message: {0}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token -> Message: {0}", e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty -> Message: {0}", e);
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().requireSubject(jwtSecret).build().parseSignedClaims(token).getPayload().getSubject();
		//return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}