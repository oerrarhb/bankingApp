package com.spring.bankingApp.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Client implements Serializable {
    @Id
    private Long id;
    private String name;
    private String email;
}
