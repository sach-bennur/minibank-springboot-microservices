package com.sachin.minibank.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.minibank.model.Account;
import com.sachin.minibank.model.User;

public interface AccountRepository extends MongoRepository<Account, String> {
	
	Account findByAccountNumber(String accountNumber);
	
	List<Account> findByEmail(String email);
	
	List<Account> findByUser(User user);

}
