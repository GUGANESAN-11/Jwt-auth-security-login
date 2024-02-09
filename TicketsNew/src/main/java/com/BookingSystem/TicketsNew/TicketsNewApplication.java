package com.BookingSystem.TicketsNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.BookingSystem.TicketsNew.Entity.Role;
import com.BookingSystem.TicketsNew.Entity.Userdata;
import com.BookingSystem.TicketsNew.Repository.UserRepository;

@SpringBootApplication
public class TicketsNewApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userrepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TicketsNewApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 
		Userdata adminAccount = userrepository.findByRole(Role.ADMIN);
		
		if(null == adminAccount) {
			Userdata user = new Userdata();
			
			user.setUserName("Gugan");
			user.setUserLocation("bangalore");
			user.setEmail("gugan.doodleblue@gmail.com");
			user.setRole(Role.ADMIN);
			user.setUserPhoneNumber("8610926934");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userrepository.save(user);
			
		}
		
	}

}
