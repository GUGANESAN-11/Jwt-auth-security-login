package com.BookingSystem.TicketsNew.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookingSystem.TicketsNew.DTO.RefreshTokenDTO;
import com.BookingSystem.TicketsNew.DTO.SigninDTO;
import com.BookingSystem.TicketsNew.DTO.SignupDTO;
import com.BookingSystem.TicketsNew.Entity.Userdata;
import com.BookingSystem.TicketsNew.Fillters.AuthenticationService;
import com.BookingSystem.TicketsNew.Fillters.JwtAuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  
	@Autowired
     private AuthenticationService authenticationService;
     
     @PostMapping("/signup")
     public ResponseEntity<Userdata> SignUP(@RequestBody SignupDTO signupDTO){
    	return ResponseEntity.ok(authenticationService.Signup(signupDTO));
     }
     
     
     @PostMapping("/signin")
     public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninDTO signinDTO){
       return ResponseEntity.ok(authenticationService.Signin(signinDTO)); 
     }
     
     
     @PostMapping("/refresh")
     public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenDTO refreshTokenDTO){
       return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenDTO));
     }
}
