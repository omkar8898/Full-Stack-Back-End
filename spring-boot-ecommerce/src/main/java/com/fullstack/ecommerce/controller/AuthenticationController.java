package com.fullstack.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.ecommerce.dto.CurrentUserDTO;
import com.fullstack.ecommerce.dto.JwtRequestDTO;
import com.fullstack.ecommerce.dto.JwtResponseDTO;
import com.fullstack.ecommerce.service.impl.CustomerDetailService;
import com.fullstack.ecommerce.util.JwtUtil;

@CrossOrigin
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerDetailService customerDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public JwtResponseDTO authenticate(@RequestBody JwtRequestDTO jwtRequestDTO) {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_MODERATOR");
		try {
			CurrentUserDTO currentUserDTO = (CurrentUserDTO) authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDTO.getUsername(),
							jwtRequestDTO.getPassword()))
					.getPrincipal();
			final CurrentUserDTO customerDetails = customerDetailService
					.loadUserByUsername(currentUserDTO.getUsername());
			if("Omkar1".equals(customerDetails.getUsername())) {
				roles = null;
				roles = new ArrayList<>();
				roles.add("ROLE_ADMIN");
			}
			return new JwtResponseDTO(jwtUtil.generateToken(customerDetails), true, currentUserDTO.getUsername(), "Logged in", roles);
		} catch (BadCredentialsException e) {
			return new JwtResponseDTO(null, false, "bad", "Invalid credentials", roles);
		}

	}

}
