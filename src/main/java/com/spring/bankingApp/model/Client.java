package com.spring.bankingApp.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "BankClients")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Client implements Serializable {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String email;
    private List<Account> accounts;
}
