package com.spring.bankingApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document(collection = "BankClients")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Client implements Serializable {

    public static final transient String SEQUENCE_NAME = "clients_sequence";

    @Id
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String address;
    private String email;
    private Account account;
}
