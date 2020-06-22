package com.fullstack.ecommerce.mapper;

import org.springframework.stereotype.Component;

import com.fullstack.ecommerce.dto.CustomerDTO;
import com.fullstack.ecommerce.entity.Customer;

@Component
public class CustomerMapper {
	
	public Customer toCustomer(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getUsername(), customerDTO.getPassword(), customerDTO.getEmail());
	}
	
	public CustomerDTO toCustomerDTO(Customer customer) {
		return new CustomerDTO(customer.getId(), customer.getUsername(), null, customer.getEmail());
	}

}
