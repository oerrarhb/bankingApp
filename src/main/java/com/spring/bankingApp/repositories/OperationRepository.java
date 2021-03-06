package com.spring.bankingApp.repositories;

import com.spring.bankingApp.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface OperationRepository extends MongoRepository<Operation, Long> {
    default Page<Operation> listOperation(Long idAccount, Pageable pageable) {
        List<Operation> operations = this.findAll().stream()
                .filter(o -> Objects.equals(o.getAccount().getIdAccount(), idAccount)).collect(Collectors.toList());
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > operations.size() ? operations.size() : (start + pageable.getPageSize());
        return new PageImpl<Operation>(operations.subList(start, end), pageable, operations.size());
    }
}
