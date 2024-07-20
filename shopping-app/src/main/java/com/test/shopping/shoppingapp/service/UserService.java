package com.test.shopping.shoppingapp.service;

import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO;

public interface UserService {

	UserLoginResponseDTO loginUser(String userName, String password);

}
