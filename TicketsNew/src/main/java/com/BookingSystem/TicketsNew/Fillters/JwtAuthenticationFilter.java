package com.BookingSystem.TicketsNew.Fillters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.BookingSystem.TicketsNew.Service.Userservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
    @Autowired
	private  Userservice userservice;
    @Autowired
	private  JWTservice jwtservice;	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String Useremail;
		//!org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")){
		    filterChain.doFilter(request, response);
		    return;
		}
		
		jwt=authHeader.substring(7);
		Useremail =jwtservice.extractUserName(jwt);
		
		if(!StringUtils.isEmpty(Useremail) && SecurityContextHolder.getContext().getAuthentication()==null) {	

			 UserDetails userDetails =userservice.userDetailsService().loadUserByUsername(Useremail);
			
			 if(jwtservice.IsTokenValid(jwt, userDetails)){
				
				 SecurityContext securitycontext =  SecurityContextHolder.createEmptyContext();
				 
				 UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						 
						     userDetails, null, userDetails.getAuthorities());
						 	
						     token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							
							 SecurityContextHolder.setContext(securitycontext);
						
			}
		}
		
	 filterChain.doFilter(request, response);
	}
}
