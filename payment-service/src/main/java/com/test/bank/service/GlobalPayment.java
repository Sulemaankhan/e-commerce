package com.test.bank.service;

import com.test.bank.dto.AccountResponseDTO;

public interface GlobalPayment {
	
	public AccountResponseDTO globalPayment(long accountNumber);

}
