package com.test.shopping.shoppingapp.feignclient;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shopping.shoppingapp.dto.AccountRequestDTO;
import com.test.shopping.shoppingapp.dto.AccountResponseDTO;



@FeignClient(name = "http://Bank-SERVICE/bankapp/accounts")
public interface BankClient {

	@GetMapping("port")
	public String getInfo();

	@PostMapping("payment")
	public AccountResponseDTO payment(@RequestParam long accountNumber);

}
