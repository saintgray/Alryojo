package com.alj.dream.auth.controller;

import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import security.AccountService;
import security.JwtUtil;

@RestController
public class TokenProvideController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AccountService accountService;
	private JwtUtil jwtUtil;
	

	
	@Autowired
	public TokenProvideController(AccountService accountService, JwtUtil jwtUtil) {
		this.accountService = accountService;
		this.jwtUtil = jwtUtil;
	}



	@PostMapping(value="/login123")
	public ResponseEntity<?> createAuthToken(	@RequestBody Map<String, Object> signInInfo, 
												HttpServletResponse resp) throws AuthenticationException {
		
		UserDetails details = accountService.loadUserByUsername(signInInfo.get("username").toString());
		String token = jwtUtil.generateToken(details);
		jwtUtil.setTokenCookie(resp, token);
	
		return ResponseEntity.ok(token);
		
	}
}
