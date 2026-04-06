package dev.rynwllngtn.agorasystem.configurations;

import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountChecking;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountSaving;
import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.repositories.account.AccountRepository;
import dev.rynwllngtn.agorasystem.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
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

            switch (random.nextInt(4)) {
                case 1 -> {
                    AccountChecking accountChecking = new AccountChecking();
                    accountChecking.setHolder(user);
                    accountChecking.setBalance(BigDecimal.ZERO);
                    accountChecking.setTransferLimit(BigDecimal.ZERO);
                    accountChecking.setTransferLimitCap(BigDecimal.ZERO);
                    accountRepository.save(accountChecking);
                }
                case 2 -> {
                    AccountSaving accountSaving = new AccountSaving();
                    accountSaving.setHolder(user);
                    accountSaving.setBalance(BigDecimal.ZERO);
                    accountSaving.setTransferLimit(BigDecimal.ZERO);
                    accountSaving.setTransferLimitCap(BigDecimal.ZERO);
                    accountRepository.save(accountSaving);
                }
                case 3 -> {
                    AccountChecking accountChecking = new AccountChecking();
                    accountChecking.setHolder(user);
                    accountChecking.setBalance(BigDecimal.ZERO);
                    accountChecking.setTransferLimit(BigDecimal.ZERO);
                    accountChecking.setTransferLimitCap(BigDecimal.ZERO);
                    AccountSaving accountSaving = new AccountSaving();
                    accountSaving.setHolder(user);
                    accountSaving.setBalance(BigDecimal.ZERO);
                    accountSaving.setTransferLimit(BigDecimal.ZERO);
                    accountSaving.setTransferLimitCap(BigDecimal.ZERO);
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