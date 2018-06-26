package com.codingsaint.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingsaint.microservices.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}