package com.codingsaint.microservices.model;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{
	
	
	private static final long serialVersionUID = 1L;

	public CustomUser(AppUser appUser){
		super(appUser.getUsername(),appUser.getPassword(),appUser.getGrantedAuthorities());
	}


}
