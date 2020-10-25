package com.spring.bankingApp.business;

import com.spring.bankingApp.entities.Account;
import com.spring.bankingApp.entities.Operation;
import org.springframework.data.domain.Page;

public interface Bank {
    Account checkAccount(String accountId);

    void transferMoney(String accountId, double amount);

    void withdrawMoney(String accountId, double amount);

    void depositMoney(String accountId1, String accountId2, double amount);

    Page<Operation> listOperations(String accountId, int page, int size);
}
