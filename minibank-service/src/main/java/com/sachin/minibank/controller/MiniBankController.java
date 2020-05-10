package com.sachin.minibank.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.minibank.dto.BenificiaryFormDto;
import com.sachin.minibank.dto.TransferFormDto;
import com.sachin.minibank.model.Account;
import com.sachin.minibank.model.Benificiary;
import com.sachin.minibank.model.Transfer;
import com.sachin.minibank.model.User;
import com.sachin.minibank.repository.AccountRepository;
import com.sachin.minibank.repository.BenificiaryRepository;
import com.sachin.minibank.repository.TransferRepository;
import com.sachin.minibank.repository.UserRepository;

@RestController
public class MiniBankController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransferRepository transferRepository;
	
	@Autowired
	BenificiaryRepository benificiaryRepository;

	@GetMapping("/getAccountDetails/{email}")
	public List<Account> getAccountDetails(@PathVariable String email) {
		
		User user = userRepository.findByEmail(email);
		
        return user.getAccounts();
	}
	
	
	@PostMapping("/healthCheck")
	public String healthCheck() {
		
		
        return "Service health check successful";
	}
	
	
	@PostMapping("/addTransfer")
	public boolean addTransfer(@RequestBody TransferFormDto transferDto) {
		
		Transfer transfer = new Transfer();
		BeanUtils.copyProperties(transferDto, transfer);
		
		Account account = accountRepository.findByAccountNumber(transferDto.getTransferFrom());
		double balance = account.getBalance() - transferDto.getTransferredMoney();
		account.setBalance(balance);
		List<Transfer> transfers = account.getTransfers();
		if(CollectionUtils.isEmpty(transfers)) {
			transfers = new ArrayList<Transfer>();
		}
		transfers.add(transfer);
		account.setTransfers(transfers);
		transferRepository.save(transfer);
		
		accountRepository.save(account);
        return true;
	}
	
	
	@PostMapping("/addBenificiary")
	public User addBenificiary(@RequestBody BenificiaryFormDto benificiaryFormDto) {
		
		Benificiary benificiary = new Benificiary();
		BeanUtils.copyProperties(benificiaryFormDto, benificiary);
		
		User user = userRepository.findByEmail(benificiaryFormDto.getUserEmail());
		Set<Benificiary> benificiaries = user.getBenificiaries();
		if(CollectionUtils.isEmpty(benificiaries)) {
			benificiaries = new HashSet<Benificiary>();
		}
		benificiaries.add(benificiary);
		user.setBenificiaries(benificiaries);
		
		benificiaryRepository.save(benificiary);
		
		userRepository.save(user);
        return user;
	}
	
	@GetMapping("/findUser/{email}")
	public User findUserByEmail(@PathVariable String email) {
		
		User user = userRepository.findByEmail(email);
		return user;
	}
}
