package com.spring.bankingApp.config;

import com.spring.bankingApp.model.Client;
import com.spring.bankingApp.repositories.AccountRepository;
import com.spring.bankingApp.repositories.ClientRepository;
import com.spring.bankingApp.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {AccountRepository.class, ClientRepository.class, OperationRepository.class})
@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, OperationRepository operationRepository, AccountRepository accountRepository) {
        return strings -> {
            clientRepository.save(new Client().withId(1L).withName("Daryl Dixon").withEmail("daryl.dixon@gmail.com"));
            clientRepository.save(new Client().withId(2L).withName("Negan Jacob").withEmail("jacob.negan@gmail.com"));
            clientRepository.save(new Client().withId(3L).withName("Rosita Sicarro").withEmail("rosita.sicarro@gmail.com"));
        };
    }
}
