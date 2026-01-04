package com.test.bank.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.test.bank.dao.AccountDao;
import com.test.bank.dto.AccountRequestDTO;
import com.test.bank.entity.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Override
	public List<AccountRequestDTO> pay(String accountNumber) {
	//	List<AccountRequestDTO> list = Arrays.asList(new AccountRequestDTO(101, "sbi"));
	//	Map<String, List<AccountRequestDTO>> map = new HashMap<String, List<AccountRequestDTO>>();
	//	map.put("101", list);

		return null;
	}

	@Override
	public Account payment(long accountNumber) {
		Account accounts=new Account();
		accounts.setAccountNumber(101);
		accounts.setBalance(90000);
		
		//AccountRequestDTO account = new AccountRequestDTO();
		//account.setAccountNumber(101);
		return accounts;
	}

}
