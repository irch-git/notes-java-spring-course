package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.DataAccessObject.IUserAccountRepository;
import com.jrp.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	IUserAccountRepository accountRepo;
	
	public UserAccount save(UserAccount user) { //needed for securityController
		return accountRepo.save(user);
	}
}


