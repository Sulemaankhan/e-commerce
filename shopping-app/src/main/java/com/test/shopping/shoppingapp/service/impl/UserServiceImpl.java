package com.test.shopping.shoppingapp.service.impl;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

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

	private static final Pattern BCRYPT_PATTERN =
			Pattern.compile("^\\$2[aby]\\$\\d\\d\\$[./0-9A-Za-z]{53}$");

	@Override
	public UserLoginResponseDTO loginUser(String userName, String password) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("Invalid username or password"));
		String stored = user.getPassword();
		boolean matches = false;
		// Only treat as BCrypt if it matches the standard format; this avoids calling
		// BCryptPasswordEncoder.matches on malformed values that trigger warnings.
		if (stored != null && BCRYPT_PATTERN.matcher(stored).matches()) {
			matches = passwordEncoder.matches(password, stored);
		} else {
			if (stored != null && stored.equals(password)) {
				matches = true;
				user.setPassword(passwordEncoder.encode(password));
				userRepository.save(user);
			}
		}
		if (!matches) {
			throw new UserNotFoundException("Invalid username or password");
		}
		String token = jwtUtil.generateToken(user.getId(), user.getUserName(), user.getRole());
		UserResponse ur = new UserResponse();
		ur.setId(user.getId());
		ur.setUserName(user.getUserName());
		ur.setRole(user.getRole());
		ur.setFirstName(user.getFirstName());
		ur.setLastName(user.getLastName());
		ur.setEmailId(user.getEmailId());
		ur.setMobileNumber(user.getMobileNumber());
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
		// Manually assign ID to avoid relying on DB auto-increment for existing schema.
		 // Create a Random object
        Random rand = new Random();
        // Generate a random number between 1000 (inclusive) and 10000 (exclusive)
        int min = 1000;
        int max = 9999;
        int randomNum = rand.nextInt(max - min + 1) + min;
		user.setId(Long.valueOf(randomNum));
		user.setUserName(request.getUserName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmailId(request.getEmailId());
		user.setMobileNumber(request.getMobileNumber());
		// Simple rule: username 'admin' becomes ADMIN, others are USER.
		String role = "USER";
		if ("admin".equalsIgnoreCase(request.getUserName())) {
			role = "ADMIN";
		}
		user.setRole(role);
		user = userRepository.save(user);
		String token = jwtUtil.generateToken(user.getId(), user.getUserName(), user.getRole());
		UserResponse ur = new UserResponse();
		ur.setId(user.getId());
		ur.setUserName(user.getUserName());
		ur.setRole(user.getRole());
		ur.setFirstName(user.getFirstName());
		ur.setLastName(user.getLastName());
		ur.setEmailId(user.getEmailId());
		ur.setMobileNumber(user.getMobileNumber());
		UserLoginResponseDTO dto = new UserLoginResponseDTO();
		dto.setToken(token);
		dto.setUser(ur);
		dto.setMessage("Registration successful");
		return dto;
	}

	@Override
	public boolean isAdmin(Long userId) {
		return userRepository.findById(userId)
				.map(User::getRole)
				.map("ADMIN"::equalsIgnoreCase)
				.orElse(false);
	}
}
