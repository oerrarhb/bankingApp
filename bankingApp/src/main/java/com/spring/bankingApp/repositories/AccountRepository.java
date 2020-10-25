package com.spring.bankingApp.repositories;

import com.spring.bankingApp.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
