package com.spring.bankingApp.events;

import com.spring.bankingApp.business.SequenceGeneratorService;
import com.spring.bankingApp.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class ClientModelListener extends AbstractMongoEventListener<Client> {
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ClientModelListener(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public void onBeforeConvert(BeforeConvertEvent<Client> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME));
        }
    }
}
