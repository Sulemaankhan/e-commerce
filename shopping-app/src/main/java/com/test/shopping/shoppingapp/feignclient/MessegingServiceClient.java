package com.test.shopping.shoppingapp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shopping.shoppingapp.dto.AccountResponseDTO;
import com.test.shopping.shoppingapp.dto.EmailDetails;

@FeignClient(name = "http://email-service/emails")
public interface MessegingServiceClient {

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details);

	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@RequestBody EmailDetails details);

}
