package com.BookingSystem.TicketsNew.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

	private String UserName;
	private String email;
	private String UserPhoneNumber;
	private String Password;
	private String UserLocation;
	
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPhoneNumber() {
		return UserPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		UserPhoneNumber = userPhoneNumber;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserLocation() {
		return UserLocation;
	}
	public void setUserLocation(String userLocation) {
		UserLocation = userLocation;
	}
	
}
