package com.sachin.minibank.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	 private String id;

	    private String email;

	    private String password;

	    private String firstName;

	    private String lastName;

	    private String mobileNumber;
	    
	    private List<AccountDto> accounts;
	    
	    private List<BenificiaryDto> benificiaries;
}
