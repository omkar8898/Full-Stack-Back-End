package com.fullstack.ecommerce.service;

import com.fullstack.ecommerce.dto.CustomerDTO;
import com.fullstack.ecommerce.entity.Customer;

public interface CustomerService {
	
	Customer findByUsername(String username);

	Customer findById(Long id);

	Customer save(CustomerDTO customerDTO);
}
