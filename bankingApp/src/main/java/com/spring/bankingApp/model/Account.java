package com.spring.bankingApp.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Accounts")
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
