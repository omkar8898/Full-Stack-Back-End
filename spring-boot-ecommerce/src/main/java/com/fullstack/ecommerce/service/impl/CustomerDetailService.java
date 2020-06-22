package com.fullstack.ecommerce.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fullstack.ecommerce.dto.CurrentUserDTO;
import com.fullstack.ecommerce.entity.Customer;
import com.fullstack.ecommerce.service.CustomerService;

@Service
public class CustomerDetailService implements UserDetailsService{
	
	@Autowired
	private CustomerService customerService;

	@Override
	public CurrentUserDTO loadUserByUsername(String username){
		Customer customer = customerService.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		if(customer==null) {
			throw new UsernameNotFoundException("Customer Not Found with Customer Name "+username);
		}
		return new CurrentUserDTO(customer.getUsername(), customer.getPassword(), grantedAuthorities, customer.getId());
	}

}
