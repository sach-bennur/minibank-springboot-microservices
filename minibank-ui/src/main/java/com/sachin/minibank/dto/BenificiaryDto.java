package com.sachin.minibank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BenificiaryDto {

	private String firstName;
	private String lastName;
	private String accountNumber;
	private String ifscCode;
	private String bank;
	private String userEmail;
}
