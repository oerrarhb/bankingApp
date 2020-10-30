package com.spring.bankingApp.business;

import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.Operation;
import org.springframework.data.domain.Page;

public interface Bank {
    Account checkAccount(String accountId);

    void depositMoney(String accountId, double amount);

    void withdrawMoney(String accountId, double amount);

    void transferMoney(String accountId1, String accountId2, double amount);

    Page<Operation> listOperations(String accountId, int page, int size);
}
