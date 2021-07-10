package com.spring.bankingApp.config;

import com.spring.bankingApp.business.Bank;
import com.spring.bankingApp.repositories.AccountRepository;
import com.spring.bankingApp.repositories.ClientRepository;
import com.spring.bankingApp.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {AccountRepository.class, ClientRepository.class, OperationRepository.class})
@Configuration
public class MongoDBConfig {

    @Autowired
    Bank bank;

    @Autowired
    MongoOperations mongoOperations;

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, OperationRepository operationRepository, AccountRepository accountRepository) {
        return strings -> {
/*            clientRepository.deleteAll();
            accountRepository.deleteAll();
            operationRepository.deleteAll();

            Account account1 = Account.builder()
                    .balance(40000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withName("Daryl Dixon").withEmail("daryl.dixon@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();

            Account account2 = Account.builder()
                    .balance(60000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withName("Negan Jacob").withEmail("negan.jacob@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();


            Account account3 = Account.builder()
                    .balance(100000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withName("Rosita Sicarro").withEmail("rosita.sicarro@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();

            Account account4 = Account.builder()
                    .balance(60000)
                    .type(AccountTypes.DEFICIT)
                    .client(new Client().withName("Rosita Sicarro").withEmail("rosita.sicarro@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();

            accountRepository.save(account1);
            accountRepository.save(account2);
            accountRepository.save(account3);
            accountRepository.save(account4);


            Client client1 = Client.builder()
                    .name("Rosita Sicarro")
                    .email("rosita.sicarro@gmail.com")
                    .accounts(List.of(account3, account4))
                    .build();


            Client client2 = Client.builder()
                    .name("Negan Jacob")
                    .email("negan.jacob@gmail.com")
                    .accounts(List.of(account2))
                    .build();

            Client client3 = Client.builder()
                    .name("Daryl Dixon")
                    .email("daryl.dixon@gmail.com")
                    .accounts(List.of(account4))
                    .build();

            clientRepository.save(client1);
            clientRepository.save(client2);
            clientRepository.save(client3);
*/
            bank.depositMoney(49L,5000);

        };
    }
}
