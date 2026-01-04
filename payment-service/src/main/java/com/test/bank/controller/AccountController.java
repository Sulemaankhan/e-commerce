package com.test.bank.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
import com.test.bank.service.GlobalPayment;
import com.test.bank.service.PaymentStrategy;
import com.test.bank.service.strategy.UtilityStrategyService;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
@Slf4j
public class AccountController {

	//private final PaymentStrategy accountService;
	private final GlobalPayment globalPayment;
	private final UtilityStrategyService utilityStrategyService;
	private final Environment environment;
	
	

//	public AccountController(GlobalPayment globalPayment,
//			UtilityStrategyService utilityStrategyService, Environment environment) {
//		super();
//		//this.accountService = accountService;
//		this.globalPayment = globalPayment;
//		this.utilityStrategyService = utilityStrategyService;
//		this.environment = environment;
//	}

	@GetMapping("port")
	public String getInfo() {
		log.info("checking load balance");
		String port = environment.getProperty("local.server.port");
		return "From server " + port;
	}

	@PostMapping("global/payment")
	public ResponseEntity<AccountResponseDTO> globalPayment(@PathVariable String type,
			@Valid @RequestParam long accountNumber) {
		log.info("Bank Service is calling");
		AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
		accountRequestDTO.setAccountNumber(101);
		// accountRequestDTO.setBankName("sbi");
		AccountResponseDTO accountResponseDTO = globalPayment.globalPayment(accountNumber);
		// String status = accountService.payment(accountNumber);
		return new ResponseEntity<AccountResponseDTO>(accountResponseDTO, HttpStatus.OK);
	}

	@PostMapping("payment")
	public ResponseEntity<AccountResponseDTO> payment(@PathVariable String mode,
			@Valid @RequestParam long accountNumber) {
		
		log.info("Bank Service is calling");
		AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
		accountRequestDTO.setAccountNumber(101);
		
		//find the payment mode
		PaymentStrategy paymentMode =utilityStrategyService.getPaymentMode(mode);
		
		//call payment service impl
		AccountResponseDTO accountResponseDTO = paymentMode.payment(accountNumber);
		//set payment mode
		accountResponseDTO.setType(mode);
		return new ResponseEntity<AccountResponseDTO>(accountResponseDTO, HttpStatus.OK);
	}
}
