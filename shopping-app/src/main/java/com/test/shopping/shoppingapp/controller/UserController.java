package com.test.shopping.shoppingapp.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.shopping.shoppingapp.dto.UserLoginRequestDTO;
import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO;
import com.test.shopping.shoppingapp.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	@Autowired
	private UserService userService;

	// logger configuration
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("login")
	public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
		logger.info("UserController    login()");
		logger.trace("UserController    login()");
		// login user with username and password, and stored into userResponseDTO obj
		UserLoginResponseDTO userResponseDTO = userService.loginUser(userLoginRequestDTO.getUserName(),
				userLoginRequestDTO.getPassword());
		// return the response
		return new ResponseEntity<UserLoginResponseDTO>(userResponseDTO, HttpStatus.OK);
	}
}
