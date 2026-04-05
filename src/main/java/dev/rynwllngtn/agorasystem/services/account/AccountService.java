package dev.rynwllngtn.agorasystem.services.account;

import dev.rynwllngtn.agorasystem.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorasystem.dtos.account.AccountUpdateRequestDTO;
import dev.rynwllngtn.agorasystem.entities.account.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService {

    public AccountResponseDTO findById(UUID id);

    public Account insert(Account account);

    public void delete(UUID id);

    public Account update(UUID id, AccountUpdateRequestDTO accountUpdateRequestDTO);

}