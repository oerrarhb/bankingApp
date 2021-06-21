package com.spring.bankingApp.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "BankAccounts")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Account implements Serializable {


    @Id
    private String idAccount;
    private AccountTypes type;
    private Date dateOfCreation;
    private double balance;
    private Client client;
    private List<Operation> operations;
}
