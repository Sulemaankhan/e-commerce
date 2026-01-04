package com.test.bank.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bank.customexception.AccountNotFountException;
import com.test.bank.dao.AccountDao;
import com.test.bank.dto.AccountRequestDTO;
import com.test.bank.dto.AccountResponseDTO;
import com.test.bank.entity.Account;
import com.test.bank.service.GlobalPayment;
import com.test.bank.service.PaymentStrategy;

@Service
public class AccountServiceImpl implements GlobalPayment {

	@Autowired
	private AccountDao accountDao;

	@Override
	public AccountResponseDTO globalPayment(long accountNumber) {
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

	//	String status = "success";
		Account account = accountDao.payment(accountNumber);

		BeanUtils.copyProperties(account, accountResponseDTO);

		if (accountResponseDTO.getAccountNumber() == accountNumber) {
			return accountResponseDTO;
		} else {
			throw new AccountNotFountException("Account number is not found   :" + accountNumber);
		}
	}
}
