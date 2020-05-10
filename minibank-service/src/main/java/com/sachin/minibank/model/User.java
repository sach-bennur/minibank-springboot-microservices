package com.sachin.minibank.model;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String mobileNumber;
    
    @DBRef
    private List<Account> accounts;
    
    @DBRef
    private Set<Role> roles;
    
    @DBRef
    private Set<Benificiary> benificiaries;

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }
}
