package com.spring.bankingApp.entities;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Operation implements Serializable {
    @Id
    private Long id;
    private String type;
    private Date dateOfOperation;
    private double amount;
    private Account account;
}
