package com.spring.bankingApp.repositories;

import com.spring.bankingApp.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, Long> {
}
