package com.BookingSystem.TicketsNew.Repository;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.BookingSystem.TicketsNew.Entity.Role;
import com.BookingSystem.TicketsNew.Entity.Userdata;


@Repository
@EnableJpaRepositories
@Configuration
public interface UserRepository extends JpaRepository<Userdata, Integer> {

	Optional<Userdata> findByEmail(String email);
	
	Userdata findByRole(Role role);
}
