package com.sachin.minibank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sachin.minibank.model.Account;
import com.sachin.minibank.model.Role;
import com.sachin.minibank.model.User;
import com.sachin.minibank.repository.AccountRepository;
import com.sachin.minibank.repository.RoleRepository;
import com.sachin.minibank.repository.UserRepository;

@SpringBootApplication
public class MiniBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniBankApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserRepository userRepository,AccountRepository accountRepository,RoleRepository roleRepository) {
        return args -> {
        	
        	List<Account> accounts = new ArrayList<Account>();
        	
        	 Role adminRole = roleRepository.findByRole("ADMIN");
             if (adminRole == null) {
                 adminRole = new Role();
                 adminRole.setRole("ADMIN");
                 roleRepository.save(adminRole);
             }
        	
        	User admin = userRepository.findByEmail("sach.bennur@gmail.com");
            if (admin == null) {
                admin = new User()
                        .setEmail("sach.bennur@gmail.com")
                        .setPassword("123456") // "123456"
                        .setFirstName("Sachin")
                        .setLastName("Bennur")
                        .setMobileNumber("123456789")
                        .setRoles(new HashSet<>(Arrays.asList(adminRole)));
                userRepository.save(admin);
            }
            
			/*
			 * User user1 = userRepository.findByEmail("test1@gmail.com"); if (user1 ==
			 * null) { user1 = new User() .setEmail("test1@gmail.com")
			 * .setPassword("123456") // "123456" .setFirstName("Test") .setLastName("1")
			 * .setMobileNumber("123456789"); userRepository.save(user1); }
			 * 
			 * User user2 = userRepository.findByEmail("test2@gmail.com"); if (user2 ==
			 * null) { user2 = new User() .setEmail("test2@gmail.com")
			 * .setPassword("123456") // "123456" .setFirstName("Test") .setLastName("2")
			 * .setMobileNumber("123456789"); userRepository.save(user2); }
			 */
            
            
            Account account1 = new Account().setAccountNumber("0234567234")
            		.setAccountType("Savings")
            		.setBalance(49000)
            		.setBranch("WhiteField")
		            .setEmail("sach.bennur@gmail.com");
            
            accountRepository.save(account1);
            accounts.add(account1);
            
            Account account2 = new Account().setAccountNumber("0434357834")
            		.setAccountType("Current")
            		.setBalance(242000)
            		.setBranch("HSR Layout")
            		.setEmail("sach.bennur@gmail.com");
            
            accountRepository.save(account2);
            accounts.add(account2);
            
            Account account3 = new Account().setAccountNumber("0134967834")
            		.setAccountType("Demat")
            		.setBalance(149000)
            		.setBranch("Kundalahalli")
            		.setEmail("sach.bennur@gmail.com");
            
            accountRepository.save(account3);
            accounts.add(account3);
            
            
            admin.setAccounts(accounts);
            userRepository.save(admin);
        	
        };

	}

}