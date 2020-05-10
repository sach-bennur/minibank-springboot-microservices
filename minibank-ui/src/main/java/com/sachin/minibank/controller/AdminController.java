package com.sachin.minibank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sachin.minibank.dto.AccountDto;
import com.sachin.minibank.dto.BenificiaryDto;
import com.sachin.minibank.dto.TransferFormData;
import com.sachin.minibank.dto.UserDto;
import com.sachin.minibank.service.client.MiniBankServiceClient;

@RestController
public class AdminController {

	@Autowired
	MiniBankServiceClient miniBankServiceClient;
	
	@GetMapping(value = {"/","/logout"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }
	
	@PostMapping(value = "/validate")
    public ModelAndView validate() {
		
		ModelAndView modelAndView = new ModelAndView("accountDashboard");
		
		List<AccountDto> accountData = miniBankServiceClient.getAccountDetails("sach.bennur@gmail.com");
		modelAndView.addObject("accountData", accountData);
        return modelAndView;
    }

    @GetMapping("/transfer")
    public ModelAndView getTransfer() {
    	ModelAndView modelAndView = new ModelAndView("transfer");
    	TransferFormData transferFormData = new TransferFormData();
    	
    	UserDto user= miniBankServiceClient.findUserByEmail("sach.bennur@gmail.com");
    	System.out.println(user);
    	AccountDto account = user.getAccounts().stream().filter(acc -> acc.getAccountType().equals("Savings")).findFirst().get();
    	transferFormData.setTransferFrom(account.getAccountNumber());
    	transferFormData.setBalance(account.getBalance()+"");
    	modelAndView.addObject("transferFormData", transferFormData);
    	modelAndView.addObject("transactionData", account.getTransfers());
    	return modelAndView;
    }
    
    
    @PostMapping("/transfer")
    public ModelAndView addTransfer(@ModelAttribute("transferFormData") TransferFormData transferFormData) {
    	ModelAndView modelAndView = new ModelAndView("transfer");
    	miniBankServiceClient.addTransfer(transferFormData);
    	UserDto user= miniBankServiceClient.findUserByEmail("sach.bennur@gmail.com");
    	AccountDto account = user.getAccounts().stream().filter(acc -> acc.getAccountType().equals("Savings")).findFirst().get();
    	List<TransferFormData> transactions = account.getTransfers();
    	modelAndView.addObject("transactionData",transactions);
    	return modelAndView;
    }

    @GetMapping("/manageBenificiary")
    public ModelAndView getBenificiary() {
    	
    	ModelAndView modelAndView = new ModelAndView("manageBenificiary");
    	BenificiaryDto benificiaryDto =new BenificiaryDto();
    	UserDto user= miniBankServiceClient.findUserByEmail("sach.bennur@gmail.com");
    	modelAndView.addObject("benificiaryData",benificiaryDto);
    	modelAndView.addObject("benificiaries", user.getBenificiaries());
    	return modelAndView;
    }
    
    @PostMapping("/manageBenificiary")
    public ModelAndView addNewBenificiary(@ModelAttribute("benificiaryData") BenificiaryDto benificiaryDto) {
    	
    	ModelAndView modelAndView = new ModelAndView("manageBenificiary");
    	benificiaryDto.setUserEmail("sach.bennur@gmail.com");
    	UserDto user = miniBankServiceClient.addBenificiary(benificiaryDto);
    	modelAndView.addObject("benificiaries", user.getBenificiaries());
    	modelAndView.addObject("benificiaryData",new BenificiaryDto());
    	return modelAndView;
    }
    
    @GetMapping(value = "/home")
    public ModelAndView homePage() {
    	
    	ModelAndView modelAndView = new ModelAndView("accountDashboard");
		
    	List<AccountDto> accountData =  miniBankServiceClient.getAccountDetails("sach.bennur@gmail.com");
		modelAndView.addObject("accountData", accountData);
        return modelAndView;
    }
    
    @GetMapping(value = "/settings")
    public ModelAndView settings() {
        return new ModelAndView("settings");
    }
    
    @GetMapping(value = "/test")
    public UserDto testService(String email) {
    	
    	return miniBankServiceClient.findUserByEmail(email);
    }
}
