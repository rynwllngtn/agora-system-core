package dev.rynwllngtn.agorasystem.services.account;

import dev.rynwllngtn.agorasystem.dtos.account.AccountCreateRequestDTO;
import dev.rynwllngtn.agorasystem.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorasystem.dtos.account.AccountUpdateRequestDTO;
import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.exceptions.database.DatabaseException;
import dev.rynwllngtn.agorasystem.repositories.account.AccountRepository;
import dev.rynwllngtn.agorasystem.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Override
    public AccountResponseDTO findById(UUID id) {
        Optional<AccountResponseDTO> user = accountRepository.findAccountById(id);
        return user.orElseThrow(() -> new DatabaseException.ResourceNotFoundException(id));
    }

    @Override
    public Account insert(AccountCreateRequestDTO accountCreateRequestDTO) {

        Account account = accountCreateRequestDTO.getAccount();
        account.setHolder(userService.findUserById(accountCreateRequestDTO.getHolder()));
        return accountRepository.save(account);
    }

    @Override
    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account update(UUID id, AccountUpdateRequestDTO accountUpdateRequestDTO) {

        try {
            Account account = accountRepository.getReferenceById(id);
            account.update(accountUpdateRequestDTO.getBalance(),
                           accountUpdateRequestDTO.getTransferLimit(),
                           accountUpdateRequestDTO.getTransferLimitCap());

            return accountRepository.save(account);
        }
        catch (EntityNotFoundException e) {
            throw new DatabaseException.ResourceNotFoundException(id);
        }
    }

}