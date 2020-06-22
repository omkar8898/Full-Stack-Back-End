package com.fullstack.ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.ecommerce.dto.ResponseDTO;

@CrossOrigin
@RestController
public class HomeController {
	
	@GetMapping("/home")
	public ResponseDTO home() {
		return new ResponseDTO(true, "Logged In");
	}

}
