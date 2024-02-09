package com.BookingSystem.TicketsNew.Fillters;

import java.security.Key;
import java.sql.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.BookingSystem.TicketsNew.Entity.Userdata;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTserviceIMPL implements JWTservice{

	@SuppressWarnings("deprecation")
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername())
			    .setIssuedAt(new Date(System.currentTimeMillis()))
			    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
			    .signWith(getSigninkey(), SignatureAlgorithm.HS256)
			    .compact();
	}
	
	@SuppressWarnings("deprecation")
	public String generateRefreshToken(Map<String,Object> extractClaims,UserDetails userDetails) {
		return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
			    .setIssuedAt(new Date(System.currentTimeMillis()))
			    .setExpiration(new Date(System.currentTimeMillis() + 604800000))
			    .signWith(getSigninkey(), SignatureAlgorithm.HS256)
			    .compact();
	}
	
	private<T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Claims extractAllClaims(String token) {
		try {
			
		//return Jwts.parserBuilder().setSigningKey(getSigninkey()).build().parseClaimsJws(token).getBody();
		return Jwts.parser().verifyWith(getSigninkey()).build().parseSignedClaims(token).getBody();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	 return null;
	}
	
	private SecretKey getSigninkey() {
		byte[] key = Decoders.BASE64.decode("7465737420696E707574");
		return Keys.hmacShaKeyFor(key);
	}
	
	public boolean IsTokenValid(String token, UserDetails userdetails) {
		  final String username=extractUserName(token);
	return  (username.equals(userdetails.getUsername()) && !IsTokenExpired(token)); 
	}
	
	private boolean IsTokenExpired(String token) {
		 return extractClaim(token, Claims::getExpiration).before(new Date(0));
	}


}
