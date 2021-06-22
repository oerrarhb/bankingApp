package com.spring.bankingApp.business;

import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.Operation;
import com.spring.bankingApp.model.OperationTypes;
import com.spring.bankingApp.repositories.AccountRepository;
import com.spring.bankingApp.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BankImplementation implements Bank {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Account checkAccount(long accountId) {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account Not Found");
        }
    }

    @Override
    public void depositMoney(long accountId, double amount) {
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
    public void withdrawMoney(long accountId, double amount) {
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
    public void transferMoney(long idAccount, long toIdAccount, double amount) {
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
    public Page<Operation> listOperations(long accountId, int page, int size) {
        return operationRepository.listOperation(accountId, PageRequest.of(page, size));
    }
}
