package com.fullstack.ecommerce.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /** CLAIMS Constant */
    private static final String CLAIMS_SUBJECT = "sub";
    private static final String CLAIMS_ISSUED_AT = "iat";
    private static final String CLAIMS_EXPIRATION = "exp";
    private static final String JWT_SECRET_KEY = "shopit";
    private static final Integer EXPIRATION = 24;
    
    
    /**
	 * Get the claim by name.
	 * @param token
	 * @param claimName
	 * @return
	 */
	private Claim getClaim(String token,String claimName) {
		JWTVerifier verifier= JWT.require(Algorithm.HMAC512(JWT_SECRET_KEY)).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT.getClaims().get(claimName);		
	}

    public String extractUsername(String token) {
		return getClaim(token, CLAIMS_SUBJECT).asString();
	}

    
	private Boolean isTokenExpired(String token) {
		return getClaim(token, CLAIMS_EXPIRATION).asDate().before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return JWT.create()
		.withClaim(CLAIMS_SUBJECT, subject)
		.withClaim(CLAIMS_ISSUED_AT, new Date(System.currentTimeMillis()))
		.withClaim(CLAIMS_EXPIRATION, getTokenExpirationTime())
		.sign(Algorithm.HMAC512(JWT_SECRET_KEY));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Date getTokenExpirationTime() {
		Integer jwtExpirationInMinute = EXPIRATION;
		//converts jwtExpirationInMinute to milliseconds
		long expirationTime= System.currentTimeMillis()+ 1000 * 60 * jwtExpirationInMinute;
		return new Date(expirationTime);
	}
}
