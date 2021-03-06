package com.codingsaint.microservices.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	private Long id;
	private String name;
	private Set<AppUser> users;
	public Role(){
		super();
	}
	
	public Role(String name) {
		super();
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "roles" )
	public Set<AppUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AppUser> users) {
		this.users = users;
	}
}