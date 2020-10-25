package com.spring.bankingApp.business;

import com.spring.bankingApp.entities.Account;
import com.spring.bankingApp.entities.Operation;
import com.spring.bankingApp.repositories.AccountRepository;
import com.spring.bankingApp.repositories.OperationRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankImplementation implements Bank {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;


    @Override
    public Account checkAccount(String accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account Not Found");
        }
    }

    @Override
    public void transferMoney(String accountId, double amount) {
        Account account = checkAccount(accountId);
        Operation transfer = Operation.builder()
                .id(Math.round(Math.random()))
                .dateOfOperation(new Date())
                .type("transfer")
                .amount(amount)
                .account(account)
                .build();
        operationRepository.save(transfer);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void withdrawMoney(String accountId, double amount) {
        Account account = checkAccount(accountId);
        Operation withdraw = Operation.builder()
                .id(Math.round(Math.random()))
                .dateOfOperation(new Date())
                .type("withdraw")
                .amount(amount)
                .account(account)
                .build();
        operationRepository.save(withdraw);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Override
    public void depositMoney(String accountId1, String accountId2, double amount) {

    }

    @Override
    public Page<Operation> listOperations(String accountId, int page, int size) {
        return null;
    }
}
