package com.sachin.minibank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionFormData {

	private String transactionType;
	private String transactionStatus;
	private String transactionAmount;
}
