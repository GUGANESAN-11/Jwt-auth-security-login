package com.BookingSystem.TicketsNew.Fillters;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BookingSystem.TicketsNew.DTO.RefreshTokenDTO;
import com.BookingSystem.TicketsNew.DTO.SigninDTO;
import com.BookingSystem.TicketsNew.DTO.SignupDTO;
import com.BookingSystem.TicketsNew.Entity.Role;
import com.BookingSystem.TicketsNew.Entity.Userdata;
import com.BookingSystem.TicketsNew.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManger;
	@Autowired
	private JWTservice jwtservice;
	
	
	public Userdata Signup(SignupDTO signupDTO) {
		
		Userdata userdata = new Userdata();
		
		userdata.setEmail(signupDTO.getEmail());
		userdata.setUserName(signupDTO.getUserName());
		userdata.setUserPhoneNumber(signupDTO.getUserPhoneNumber());
		userdata.setUserLocation(signupDTO.getUserLocation());
		userdata.setRole(Role.USER);
		userdata.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
		return userrepository.save(userdata);
		
	}
	 
 public JwtAuthenticationResponse Signin(SigninDTO signinDTO ){
 
	 authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(signinDTO.getEmail(),signinDTO.getPassword()));
	  
	  var userdata = userrepository.findByEmail(signinDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
	  var jwt = jwtservice.generateToken(userdata);
	  var refreshToken = jwtservice.generateRefreshToken(new HashMap<>(), userdata);
	 
	 JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
	 
	 jwtAuthenticationResponse.setToken(jwt);
	 jwtAuthenticationResponse.setRefreshToken(refreshToken);
	 return jwtAuthenticationResponse;
	   
}
 
 public JwtAuthenticationResponse refreshToken(RefreshTokenDTO refreshTokenDTO ){
	 String useremaiL = jwtservice.extractUserName(refreshTokenDTO.getToken());
	 Userdata user= userrepository.findByEmail(useremaiL).orElseThrow();
	 if(jwtservice.IsTokenValid(refreshTokenDTO.getToken(), user)) {
		   var jwt = jwtservice.generateToken(user);
		   
		   JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		   
		   jwtAuthenticationResponse.setToken(jwt);
		   jwtAuthenticationResponse.setRefreshToken(refreshTokenDTO.getToken());
		   return jwtAuthenticationResponse;
	 }
	 
	 return null;
 }
}
