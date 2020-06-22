package com.fullstack.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.ecommerce.dto.CustomerDTO;
import com.fullstack.ecommerce.dto.ResponseDTO;
import com.fullstack.ecommerce.service.CustomerService;

@CrossOrigin
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("customer")
	public ResponseDTO saveOrUpdateTrader(@RequestBody CustomerDTO customerDTO){
		if(customerService.save(customerDTO)!=null)
			return new ResponseDTO(true, "Customer Details Saved Successfully");
		return new ResponseDTO(false, "Could not save Customer Details");
	}
			
}
