package com.sachin.minibank.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "account")
public class Account {

	@Id
	private String accountNumber;
	
	private String accountType;
	
	private double balance;
	
	private String branch;
	
	private String email;
	
	private User user;
	
	private List<Transfer> transfers;
	
	
}
