package com.sachin.minibank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto {

	private String accountNumber;
	
	private String accountType;
	
	private double balance;
	
	private String branch;
	
	
}
