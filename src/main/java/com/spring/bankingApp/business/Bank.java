package com.spring.bankingApp.business;

import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.Operation;
import org.springframework.data.domain.Page;

public interface Bank {
    Account checkAccount(long accountId);

    void depositMoney(long accountId, double amount);

    void withdrawMoney(long accountId, double amount);

    void transferMoney(long accountId1, long accountId2, double amount);

    Page<Operation> listOperations(long accountId, int page, int size);
}
