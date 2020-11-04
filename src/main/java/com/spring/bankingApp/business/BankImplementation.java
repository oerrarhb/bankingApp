package com.spring.bankingApp.business;

import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.Operation;
import com.spring.bankingApp.model.OperationTypes;
import com.spring.bankingApp.repositories.AccountRepository;
import com.spring.bankingApp.repositories.OperationRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account Not Found");
        }
    }

    @Override
    public void depositMoney(String accountId, double amount) {
        var account = checkAccount(accountId);
        var transfer = Operation.builder()
                .dateOfOperation(new Date())
                .type(OperationTypes.DEPOSIT)
                .amount(amount)
                .account(account)
                .build();
        operationRepository.save(transfer);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void withdrawMoney(String accountId, double amount) {
        var account = checkAccount(accountId);
        var withdraw = Operation.builder()
                .dateOfOperation(new Date())
                .type(OperationTypes.WITHDRAW)
                .amount(amount)
                .account(account)
                .build();
        operationRepository.save(withdraw);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Override
    public void transferMoney(String idAccount, String toIdAccount, double amount) {
        var account = checkAccount(idAccount);
        var transfer = Operation.builder()
                .dateOfOperation(new Date())
                .type(OperationTypes.TRANSFER)
                .amount(amount)
                .account(account)
                .build();
        depositMoney(toIdAccount, amount);
        withdrawMoney(idAccount, amount);
        operationRepository.save(transfer);
    }

    @Override
    public Page<Operation> listOperations(String accountId, int page, int size) {
        return operationRepository.listOperation(accountId, PageRequest.of(page, size));
    }
}
