package com.spring.bankingApp.config;

import com.spring.bankingApp.business.BankImplementation;
import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.AccountTypes;
import com.spring.bankingApp.model.Client;
import com.spring.bankingApp.model.Operation;
import com.spring.bankingApp.model.OperationTypes;
import com.spring.bankingApp.repositories.AccountRepository;
import com.spring.bankingApp.repositories.ClientRepository;
import com.spring.bankingApp.repositories.OperationRepository;
import com.spring.bankingApp.services.SequenceGeneratorService;
import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {AccountRepository.class, ClientRepository.class, OperationRepository.class})
@Configuration
public class MongoDBConfig {

    BankImplementation bankImplementation = new BankImplementation();


    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, OperationRepository operationRepository, AccountRepository accountRepository) {
        return strings -> {
            Account account1 = Account.builder()
                    .balance(40000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withId(1L).withName("Daryl Dixon").withEmail("daryl.dixon@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();

            Account account2 = Account.builder()
                    .balance(60000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withId(2L).withName("Negan Jacob").withEmail("negan.jacob@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();


            Account account3 = Account.builder()
                    .balance(100000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withId(3L).withName("Rosita Sicarro").withEmail("rosita.sicarro@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();

            Account account4 = Account.builder()
                    .balance(60000)
                    .type(AccountTypes.DEFICIT)
                    .client(new Client().withId(3L).withName("Rosita Sicarro").withEmail("rosita.sicarro@gmail.com"))
                    .dateOfCreation(new Date())
                    .build();

            Operation operation1 = Operation.builder()
                    .dateOfOperation(new Date())
                    .account(account1)
                    .type(OperationTypes.DEPOSIT)
                    .amount(5000)
                    .build();
            Operation operation2 = Operation.builder()
                    .dateOfOperation(new Date())
                    .account(account1)
                    .type(OperationTypes.WITHDRAW)
                    .amount(1000)
                    .build();
            Operation operation3 = Operation.builder()
                    .dateOfOperation(new Date())
                    .account(account3)
                    .type(OperationTypes.WITHDRAW)
                    .amount(789)
                    .build();


            operationRepository.saveAll(List.of(operation1, operation2, operation3));
            accountRepository.saveAll(List.of(account1.withOperations(List.of(operation1, operation2)), account2, account3.withOperations(List.of(operation3)), account4));

//            clientRepository.save(new Client().withId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME)).withName("Daryl Dixon").withEmail("daryl.dixon@gmail.com").withAccounts(List.of(account1)));
//            clientRepository.save(new Client().withId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME)).withName("Negan Jacob").withEmail("jacob.negan@gmail.com").withAccounts(List.of(account2)));
//            clientRepository.save(new Client().withId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME)).withName("Rosita Sicarro").withEmail("rosita.sicarro@gmail.com").withAccounts(List.of(account3, account4)));*/

/*            accountRepository.save(Account.builder()
                    .balance(40000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withId(1L).withName("Daryl Dixon").withEmail("daryl.dixon@gmail.com"))
                    .dateOfCreation(new Date())
                    .idAccount("123456")
                    .build());

            accountRepository.save(Account.builder()
                    .balance(60000)
                    .type(AccountTypes.CURRENT)
                    .client(new Client().withId(2L).withName("Negan Jacob").withEmail("negan.jacob@gmail.com"))
                    .dateOfCreation(new Date())
                    .idAccount("123789")
                    .build());*/
        };
    }
}
