package com.sachin.minibank.model;

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
@Document(collection = "benificiary")
public class Benificiary {

	private String firstName;
	private String lastName;
	
	@Id
	private String accountNumber;
	private String ifscCode;
	private String bank;
	private User user;
}
