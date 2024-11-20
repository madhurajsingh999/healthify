package com.healthify.config;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

import javax.crypto.spec.SecretKeySpec;

@Component
public class TokenProvider {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expirationms}")
	private long tokenValidityInMilliseconds;

	private static final String AUTHORITIES_KEY = "role";


	public Authentication getAuthentication(String token) {
		if (StringUtils.isEmpty(token) || !validateToken(token)) {
			throw new BadCredentialsException("Invalid token");
		}
		Claims claims = extractAllClaims(token);

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	public String getSubject(String token) {
		if (StringUtils.isEmpty(token) || !validateToken(token)) {
			throw new BadCredentialsException("Invalid token");
		}
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
	}
	
	public String getAuthorities(String token) {
		if (StringUtils.isEmpty(token) || !validateToken(token)) {
			throw new BadCredentialsException("Invalid token");
		}
		Claims claims = extractAllClaims(token);
		return claims.get(AUTHORITIES_KEY).toString();

	}

	public boolean validateToken(String authToken) {
		try {
			log.info("TOKE={}",authToken);
			Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.info("Invalid JWT signature.");
			log.trace("Invalid JWT signature trace: {0}", e);
		} catch (MalformedJwtException e) {
			log.info("Invalid JWT token.");
			log.trace("Invalid JWT token trace: {0}", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT token.");
			log.trace("Expired JWT token trace: {0}", e);
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
			log.info("Unsupported JWT token.");
			log.trace("Unsupported JWT token trace: {0}", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT token compact of handler are invalid.");
			log.trace("JWT token compact of handler are invalid trace: {0}", e);
		}
		return false;
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getPayload();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}