package com.sachin.minibank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sachin.minibank.model.Transfer;

public interface TransferRepository extends MongoRepository<Transfer, String> {

}
