package dev.rynwllngtn.agorasystem.services.account;

import dev.rynwllngtn.agorasystem.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorasystem.dtos.account.AccountUpdateRequestDTO;
import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.exceptions.database.DatabaseException;
import dev.rynwllngtn.agorasystem.repositories.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponseDTO findById(UUID id) {
        Optional<AccountResponseDTO> user = accountRepository.findAccountById(id);
        return user.orElseThrow(() -> new DatabaseException.ResourceNotFoundException(id));
    }

    @Override
    public Account insert(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account update(UUID id, AccountUpdateRequestDTO accountUpdateRequestDTO) {
        Account account = accountRepository.getReferenceById(id);
        account.update(accountUpdateRequestDTO);
        return accountRepository.save(account);
    }

}