package com.test.shopping.shoppingapp.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.shopping.shoppingapp.customexception.UserNotFoundException;
import com.test.shopping.shoppingapp.dto.AccountResponseDTO;
import com.test.shopping.shoppingapp.dto.EmailDetails;
import com.test.shopping.shoppingapp.dto.UserLoginResponseDTO;
import com.test.shopping.shoppingapp.entity.User;
import com.test.shopping.shoppingapp.feignclient.MessegingServiceClient;
import com.test.shopping.shoppingapp.repo.UserRepository;
import com.test.shopping.shoppingapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	MessegingServiceClient messegingServiceClient;

	@Override
	public UserLoginResponseDTO loginUser(String userName, String password) {
		UserLoginResponseDTO userResponseDTO = null;
		// login user by username and password
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if (user != null) {

			userResponseDTO = new UserLoginResponseDTO();
			// if if validate username and password and copy user obj to userResponseDTO
			BeanUtils.copyProperties(user, userResponseDTO);

			// return userResponseDTO
			return userResponseDTO;
		}
		// if username and password invalidate throw error
		//email service to notify
		EmailDetails email=new EmailDetails();
		email.setMsgBody("User unavailable... try again");
		email.setSubject("User not found");
		email.setRecipient("messup985@gmail.com");
		String emailStatus = messegingServiceClient.sendMail(email);
		if(StringUtils.isNotEmpty(emailStatus)) {
			System.out.println("........................");
		}
		throw new UserNotFoundException("Invalid User    :" + userName);
		
	}
}
