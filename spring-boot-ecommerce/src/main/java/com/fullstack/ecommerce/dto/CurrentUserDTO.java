package com.fullstack.ecommerce.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class CurrentUserDTO extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CurrentUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Long id) {
		super(username, password, authorities);
		this.id = id;
	}
	
}
