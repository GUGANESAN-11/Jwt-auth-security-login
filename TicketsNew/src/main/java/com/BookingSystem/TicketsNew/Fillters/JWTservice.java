package com.BookingSystem.TicketsNew.Fillters;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

//import org.springframework.security.core.userdetails.UserDetails;

import com.BookingSystem.TicketsNew.Entity.Userdata;

public interface JWTservice {
	
	public String generateToken(UserDetails userDetails);
	
	public String extractUserName(String token);
	
	public boolean IsTokenValid(String token, UserDetails userDetails);
	
	public String generateRefreshToken(Map<String,Object> extractClaims,UserDetails userDetails);

}
