package com.test.bank.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.bank.customexception.AccountNotFountException;
import com.test.bank.dao.AccountDao;
import com.test.bank.dto.AccountResponseDTO;
import com.test.bank.entity.Account;
import com.test.bank.service.PaymentStrategy;

import lombok.AllArgsConstructor;

@Component("UPI")
@AllArgsConstructor
public class UPIServiceImpl implements PaymentStrategy{
	
	private final AccountDao accountDao;

	@Override
	public AccountResponseDTO payment(long accountNumber) {
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

		//	String status = "success";
			Account account = accountDao.payment(accountNumber);

			BeanUtils.copyProperties(account, accountResponseDTO);
			System.out.println("UPI payment Service called.");
			if (accountResponseDTO.getAccountNumber() == accountNumber) {
				return accountResponseDTO;
			} else {
				throw new AccountNotFountException("Account number is not found   :" + accountNumber);
			}
		}
}
