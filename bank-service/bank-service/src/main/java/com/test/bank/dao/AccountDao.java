package com.test.bank.dao;

import java.util.List;

import com.test.bank.dto.AccountRequestDTO;
import com.test.bank.dto.AccountResponseDTO;
import com.test.bank.entity.Account;

public interface AccountDao {

	Account payment(long accountNumber);

	List<AccountRequestDTO> pay(String accountNumber);

}
