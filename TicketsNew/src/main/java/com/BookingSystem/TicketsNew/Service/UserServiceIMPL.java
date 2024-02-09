package com.BookingSystem.TicketsNew.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.BookingSystem.TicketsNew.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements Userservice{
	
	 @Autowired
	 private  UserRepository userRepository;


	 @Override
	 public UserDetailsService userDetailsService() {
		 
		 return new UserDetailsService() {	
			 
			 @Override
			 public UserDetails loadUserByUsername(String username) {
				 				 
				  return userRepository.findByEmail(username).orElseThrow(
		                   () -> new UsernameNotFoundException("User Not Found with -> username or email: " + username));
			 }
	 };
   }
}
