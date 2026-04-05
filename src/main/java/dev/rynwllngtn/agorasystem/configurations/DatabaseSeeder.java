package dev.rynwllngtn.agorasystem.configurations;

import dev.rynwllngtn.agorasystem.dtos.user.UserReferenceDTO;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountChecking;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountSaving;
import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.repositories.account.AccountRepository;
import dev.rynwllngtn.agorasystem.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) {
            return;
        }

        Random random = new Random();
        for (int i = 1; i <= 10; i++) {

            User user = buildUser(String.format("%011d", random.nextInt(1000000000)),
                                  String.format("User Number %d", i),
                                  String.format("user%d@email.com", i));

            userRepository.save(user);
            UserReferenceDTO userReferenceDTO = new UserReferenceDTO(user);

            switch (random.nextInt(4)) {
                case 1 -> {
                    AccountChecking accountChecking = new AccountChecking(userReferenceDTO);
                    accountRepository.save(accountChecking);
                }
                case 2 -> {
                    AccountSaving accountSaving = new AccountSaving(userReferenceDTO);
                    accountRepository.save(accountSaving);
                }
                case 3 -> {
                    AccountChecking accountChecking = new AccountChecking(userReferenceDTO);
                    AccountSaving accountSaving = new AccountSaving(userReferenceDTO);
                    accountRepository.saveAll(Arrays.asList(accountChecking, accountSaving));
                }
            }
        }
    }

    private User buildUser(String cpf, String name, String email) {
        User user = new User();
        user.setCpf(cpf);
        user.setPassword("password");
        user.setUserName(name);
        user.setBirthDate(LocalDate.now());
        return user;
    }

}