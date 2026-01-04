package com.test.bank.service;

import com.test.bank.dto.AccountResponseDTO;

public interface PaymentStrategy {
	public AccountResponseDTO payment(long accountNumber);
}
