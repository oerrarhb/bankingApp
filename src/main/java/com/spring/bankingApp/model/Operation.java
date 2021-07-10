package com.spring.bankingApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "BankOperations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Operation implements Serializable {

    public static final transient String SEQUENCE_NAME = "operations_sequence";

    @Id
    private long id;
    private OperationTypes type;
    private Date dateOfOperation;
    private double amount;
    private Account account;
}
