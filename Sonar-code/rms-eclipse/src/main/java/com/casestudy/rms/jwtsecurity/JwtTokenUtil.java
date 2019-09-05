package com.casestudy.rms.jwtsecurity;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Jwt Utility methods. 
 * 
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY =(long)5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	/** Retrieve user-name from jwt token. 
	 * 
	 * @param token - input token for jwt.
	 * @return username from payload of token.
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/** Retrive expiration date form jwt token.
	 * 
	 * @param token - input token for jwt.
	 * @return expiration date.
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	/** Return claim from token.
	 * 
	 * @param <T>
	 * @param token - input token for jwt.
	 * @param claimsResolver
	 * @return claim 
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	/** Return all claim from token.
	 * 
	 * @param token - input token for jwt.
	 * @return claims.
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * check if the token has expired. 
	 * @param token - input token for jwt.
	 * @return booleans - token expired or not
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * generate token for user 
	 * @param userDetails - inbuilt class of spring security
	 * @return  generated token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/** Generate the token.
	 * 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID.
	 * 2. Sign the JWT using the HS512 algorithm and secret key.
	 * 3. compact JWT to URL safe-string
	 * @param claims -
	 * @param subject -
	 * @return generated token.
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	/** Validate the token.
	 * 
	 * @param token - generated token as input.
	 * @param userDetails - inbuilt class of spring security contains user details.
	 * @return boolean - based on token validation.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}