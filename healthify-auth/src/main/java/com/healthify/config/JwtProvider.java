package com.healthify.config;

import java.security.Key;
import java.util.Base64;
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

import javax.crypto.spec.SecretKeySpec;

//@Component
public class JwtProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationms}")
	private int jwtExpiration;
	private static final String AUTHORITIES_KEY = "role";

	public String generateJwtToken(Authentication authentication) {

		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
		Date cdate=new Date();
		long now = cdate.getTime();
		Date validity = new Date(now +jwtExpiration);
		byte[] decodedKey = Base64.getDecoder().decode(jwtSecret);
		Key key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());

		return Jwts.builder().subject(authentication.getName())
				.claim("role",authorities)
				.issuedAt(cdate)
				.expiration(validity)
				.signWith(key).compact();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			//Jwts.parser().requireSubject(jwtSecret).build().parseSignedClaims(authToken).getPayload();
			Jwts.parser().requireSubject(jwtSecret).build().parse(authToken);
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