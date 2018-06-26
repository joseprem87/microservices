package com.codingsaint.microservices.service;
 import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codingsaint.microservices.model.AppUser;
import com.codingsaint.microservices.model.CustomUser;
import com.codingsaint.microservices.model.Role;
import com.codingsaint.microservices.repository.AppUserRepository;
@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired 
	private AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser user= appUserRepository.findByUsername(userName);
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();		
		for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            CustomUser customUser= new  CustomUser(user);
			return customUser;
        }
		return null;
	}

}
