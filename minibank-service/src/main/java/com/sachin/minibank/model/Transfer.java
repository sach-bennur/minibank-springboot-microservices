package com.sachin.minibank.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "transfer")
public class Transfer {

	private String payeeName;
	private String accountNumber;
	private String ifscCode;
	private double transferredMoney;
	private String comment;
	private String transferFrom;
	private String accountType;
}
