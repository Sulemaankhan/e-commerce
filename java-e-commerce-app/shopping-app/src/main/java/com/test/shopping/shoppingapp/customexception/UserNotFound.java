package com.test.shopping.shoppingapp.customexception;

import com.test.shopping.shoppingapp.entity.User;

public class UserNotFound extends RuntimeException {
	public UserNotFound(String message) {
		super(message);
	}

}
