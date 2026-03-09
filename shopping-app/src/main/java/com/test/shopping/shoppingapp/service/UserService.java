package com.test.shopping.shoppingapp.service;

import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO;
import com.test.shopping.shoppingapp.dto.UserRegisterRequestDTO;

public interface UserService {

	UserLoginResponseDTO loginUser(String userName, String password);

	UserLoginResponseDTO register(UserRegisterRequestDTO request);

	boolean isAdmin(Long userId);

}
