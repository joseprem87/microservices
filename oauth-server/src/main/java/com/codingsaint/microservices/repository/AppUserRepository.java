package com.codingsaint.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.codingsaint.microservices.model.AppUser;
 
public interface AppUserRepository extends CrudRepository<AppUser, Long>{
	AppUser findByUsername(String username);

}
