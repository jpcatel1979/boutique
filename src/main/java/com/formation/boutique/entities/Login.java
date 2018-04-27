package com.formation.boutique.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Login {
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String password;

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Login () {}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Client.get_SHA_512_SecurePassword(password);
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

	
		
}
