package com.test.shopping.shoppingapp.controller;

import jakarta.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.shopping.shoppingapp.dto.UserLoginRequestDTO;
import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO;
import com.test.shopping.shoppingapp.dto.UserRegisterRequestDTO;
import com.test.shopping.shoppingapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
		logger.info("UserController login()");
		UserLoginResponseDTO userResponseDTO = userService.loginUser(userLoginRequestDTO.getUserName(),
				userLoginRequestDTO.getPassword());
		if(ObjectUtils.isNotEmpty(userResponseDTO) && userLoginRequestDTO.getUserName()!=null) {
			logger.info("User Login success.......");
		}
		return ResponseEntity.ok(userResponseDTO);
	}

	@PostMapping("/register")
	public ResponseEntity<UserLoginResponseDTO> register(@Valid @RequestBody UserRegisterRequestDTO request) {
		logger.info("UserController register()");
		UserLoginResponseDTO response = userService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
