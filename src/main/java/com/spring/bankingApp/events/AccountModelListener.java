package com.spring.bankingApp.events;

import com.spring.bankingApp.business.SequenceGeneratorService;
import com.spring.bankingApp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class AccountModelListener extends AbstractMongoEventListener<Account> {
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public AccountModelListener(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public void onBeforeConvert(BeforeConvertEvent<Account> event) {
        if (event.getSource().getIdAccount() < 1) {
            event.getSource().setIdAccount(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        }
    }
}