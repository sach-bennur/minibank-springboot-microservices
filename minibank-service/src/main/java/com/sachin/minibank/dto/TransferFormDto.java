package com.sachin.minibank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferFormDto {

	private String payeeName;
	private String accountNumber;
	private String ifscCode;
	private double transferredMoney;
	private String comment;
	private String transferFrom;
	private String accountType;
}
