package com.sachin.minibank.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferFormData {

	private String payeeName;
	private String accountNumber;
	private String ifscCode;
	private double transferredMoney;
	private String comment;
	private String accountType;
	private String transferFrom;
	private String balance;
}
