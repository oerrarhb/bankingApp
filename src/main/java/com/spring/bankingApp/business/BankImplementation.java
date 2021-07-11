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


    @Autowired
    private OperationService operationService;

    @Autowired
    private ClientService clientService;


    @Override
    public Account checkAccount(Long accountId) {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account Not Found");
        }
    }

    @Override
    public void depositMoney(Long accountId, double amount) {
        var account = accountRepository.findById(accountId).orElse(null);
        var transfer = Operation.builder()
                .dateOfOperation(new Date())
                .type(OperationTypes.DEPOSIT)
                .amount(amount)
                .account(account)
                .build();
        operationService.save(transfer);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        var client = clientService.findById(account.getClient().getId());
        client.setAccount(account);
        clientService.save(client);

    }

    @Override
    public void withdrawMoney(Long accountId, double amount) {
        var account = accountRepository.findById(accountId).orElse(null);
        var withdraw = Operation.builder()
                .dateOfOperation(new Date())
                .type(OperationTypes.WITHDRAW)
                .amount(amount)
                .account(account)
                .build();
        operationService.save(withdraw);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        var client = clientService.findById(account.getClient().getId());
        client.setAccount(account);
        clientService.save(client);
    }

    @Override
    public void transferMoney(Long idAccount, Long toIdAccount, double amount) {
        var account = accountRepository.findById(idAccount).orElse(null);
        var transfer = Operation.builder()
                .dateOfOperation(new Date())
                .type(OperationTypes.TRANSFER)
                .amount(amount)
                .account(account)
                .build();
        depositMoney(toIdAccount, amount);
        withdrawMoney(idAccount, amount);
        operationService.save(transfer);
    }

    @Override
    public Page<Operation> listOperations(Long accountId, int page, int size) {
        return operationRepository.listOperation(accountId, PageRequest.of(page, size));
    }
}
