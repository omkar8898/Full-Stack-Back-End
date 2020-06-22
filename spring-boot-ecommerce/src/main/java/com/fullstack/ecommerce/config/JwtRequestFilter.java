package com.fullstack.ecommerce.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fullstack.ecommerce.dto.CurrentUserDTO;
import com.fullstack.ecommerce.service.impl.CustomerDetailService;
import com.fullstack.ecommerce.util.JwtUtil;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerDetailService customerDetailService;
	
	private static final String BEARER = "Bearer ";

	private static final String AUTHORIZATION = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader(AUTHORIZATION);

		String traderName = null;
		String jwt = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER)) {
			jwt = requestTokenHeader.substring(7);
			try {
				traderName = jwtUtil.extractUsername(jwt);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

		if (traderName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			CurrentUserDTO userDetails = this.customerDetailService.loadUserByUsername(traderName);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		filterChain.doFilter(request, response);
	}

}
