package com.sachin.minibank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.minibank.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmail(String email);

}
