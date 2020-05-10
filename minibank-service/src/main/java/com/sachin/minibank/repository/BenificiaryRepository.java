package com.sachin.minibank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.minibank.model.Benificiary;

public interface BenificiaryRepository extends MongoRepository<Benificiary, String> {

}
