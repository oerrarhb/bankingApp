package com.spring.bankingApp.repositories;

import com.spring.bankingApp.model.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, Long> {
}
