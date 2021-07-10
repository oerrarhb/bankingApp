package com.spring.bankingApp.business;

import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.Operation;
import org.springframework.data.domain.Page;

public interface Bank {
    Account checkAccount(Long accountId);

    void depositMoney(Long accountId, double amount);

    void withdrawMoney(Long accountId, double amount);

    void transferMoney(Long accountId1, Long accountId2, double amount);

    Page<Operation> listOperations(Long accountId, int page, int size);
}
