package com.BookingSystem.TicketsNew.DTO;

import lombok.Data;

@Data
public class RefreshTokenDTO {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
