package com.spring.bankingApp.repositories;

import com.spring.bankingApp.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
}
