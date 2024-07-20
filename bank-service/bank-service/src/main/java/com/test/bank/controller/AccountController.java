package com.test.bank.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.bank.dto.AccountRequestDTO;
import com.test.bank.dto.AccountResponseDTO;
import com.test.bank.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	@Autowired
	Environment environment;

	@GetMapping("port")
	public String getInfo() {
		logger.info("checking load balance");
		String port = environment.getProperty("local.server.port");
		return "From server " + port;
	}

	@PostMapping("payment")
	public ResponseEntity<AccountResponseDTO> payment(@Valid @RequestParam long accountNumber) {
		logger.info("Bank Service is calling");
		AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
		accountRequestDTO.setAccountNumber(101);
		// accountRequestDTO.setBankName("sbi");
		AccountResponseDTO accountResponseDTO = accountService.payment(accountNumber);
		// String status = accountService.payment(accountNumber);
		return new ResponseEntity<AccountResponseDTO>(accountResponseDTO, HttpStatus.OK);
	}
}
