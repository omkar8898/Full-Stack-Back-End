package com.fullstack.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fullstack.ecommerce.dao.CustomerRepository;
import com.fullstack.ecommerce.dto.CustomerDTO;
import com.fullstack.ecommerce.entity.Customer;
import com.fullstack.ecommerce.mapper.CustomerMapper;
import com.fullstack.ecommerce.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username).orElse(null);
	}

	@Override
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public Customer save(CustomerDTO customerDTO) {
		customerDTO.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
		return customerRepository.save(customerMapper.toCustomer(customerDTO));
	}

}
