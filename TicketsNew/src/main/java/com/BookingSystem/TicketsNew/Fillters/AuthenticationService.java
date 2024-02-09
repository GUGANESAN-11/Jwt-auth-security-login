package com.BookingSystem.TicketsNew.Fillters;

import com.BookingSystem.TicketsNew.DTO.RefreshTokenDTO;
import com.BookingSystem.TicketsNew.DTO.SigninDTO;
import com.BookingSystem.TicketsNew.DTO.SignupDTO;
import com.BookingSystem.TicketsNew.Entity.Userdata;

public interface AuthenticationService {

	
    public Userdata Signup(SignupDTO signupDTO);
    
    public JwtAuthenticationResponse Signin(SigninDTO signinDTO );

    public JwtAuthenticationResponse refreshToken(RefreshTokenDTO refreshTokenDTO);

}
