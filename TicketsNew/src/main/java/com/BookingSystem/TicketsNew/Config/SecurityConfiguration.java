package com.BookingSystem.TicketsNew.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.BookingSystem.TicketsNew.Entity.Role;
import com.BookingSystem.TicketsNew.Fillters.JwtAuthenticationFilter;
import com.BookingSystem.TicketsNew.Service.Userservice;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{
	
    @Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
	private Userservice userservice;
    

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    
//    	http.csrf(AbstractHttpConfigurer::disable)
//    	    .authorizeHttpRequests(request ->request.requestMatchers("/api/v1/auth/**")
//    	    .permitAll()
//    	    .requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
//    	    .requestMatchers("/api/v1/user").hasAnyAuthority(Role.USER.name())
//    	    .anyRequest().authenticated())
//    	    
//    	 .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//    	 .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
//    	 );     	    		
//    	    
//    	return http.build();
//    }
    
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
       authenticationProvider.setUserDetailsService(userservice.userDetailsService());
       authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
      }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager AuthenticationManager(AuthenticationConfiguration config) throws Exception {
          return config.getAuthenticationManager();
    }
}
