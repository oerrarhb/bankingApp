package com.spring.bankingApp.repositories;

import com.spring.bankingApp.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, Long> {
}
