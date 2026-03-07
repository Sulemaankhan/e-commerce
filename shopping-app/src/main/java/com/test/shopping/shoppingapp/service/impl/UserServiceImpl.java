package com.test.shopping.shoppingapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.shopping.shoppingapp.customexception.UserNotFoundException;
import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO;
import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO.UserResponse;
import com.test.shopping.shoppingapp.dto.UserRegisterRequestDTO;
import com.test.shopping.shoppingapp.entity.User;
import com.test.shopping.shoppingapp.repo.UserRepository;
import com.test.shopping.shoppingapp.security.JwtUtil;
import com.test.shopping.shoppingapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserLoginResponseDTO loginUser(String userName, String password) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("Invalid username or password"));
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UserNotFoundException("Invalid username or password");
		}
		String token = jwtUtil.generateToken(user.getId(), user.getUserName());
		UserResponse ur = new UserResponse();
		ur.setId(user.getId());
		ur.setUserName(user.getUserName());
		UserLoginResponseDTO dto = new UserLoginResponseDTO();
		dto.setToken(token);
		dto.setUser(ur);
		return dto;
	}

	@Override
	public UserLoginResponseDTO register(UserRegisterRequestDTO request) {
		Optional<User> existing = userRepository.findByUserName(request.getUserName());
		if (existing.isPresent()) {
			throw new IllegalArgumentException("Username already exists");
		}
		User user = new User();
		user.setUserName(request.getUserName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user = userRepository.save(user);
		String token = jwtUtil.generateToken(user.getId(), user.getUserName());
		UserResponse ur = new UserResponse();
		ur.setId(user.getId());
		ur.setUserName(user.getUserName());
		UserLoginResponseDTO dto = new UserLoginResponseDTO();
		dto.setToken(token);
		dto.setUser(ur);
		dto.setMessage("Registration successful");
		return dto;
	}
}
