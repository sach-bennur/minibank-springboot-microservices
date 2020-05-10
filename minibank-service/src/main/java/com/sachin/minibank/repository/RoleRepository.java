package com.sachin.minibank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.minibank.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}
