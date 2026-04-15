package dev.rynwllngtn.agorabank.services.account;

import dev.rynwllngtn.agorabank.dtos.account.AccountCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorabank.dtos.account.DepositRequestDTO;
import dev.rynwllngtn.agorabank.dtos.account.WithdrawalRequestDTO;
import dev.rynwllngtn.agorabank.entities.account.Account;
import dev.rynwllngtn.agorabank.entities.user.User;
import dev.rynwllngtn.agorabank.exceptions.database.DatabaseException.ResourceNotFoundException;
import dev.rynwllngtn.agorabank.mappers.account.AccountMapper;
import dev.rynwllngtn.agorabank.repositories.account.AccountRepository;
import dev.rynwllngtn.agorabank.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserService userService;

    @Override
    public Account findById(UUID id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(
                () -> new ResourceNotFoundException(Account.class, id));
    }

    @Override
    public AccountResponseDTO getResponseById(UUID id) {
        Optional<AccountResponseDTO> accountResponseDTO = accountRepository.getResponseById(id);
        return accountResponseDTO.orElseThrow(
                () -> new ResourceNotFoundException(Account.class, id));
    }

    @Override
    public AccountResponseDTO insert(AccountCreateRequestDTO createRequestDTO) {
        User holder = userService.findReferenceById(createRequestDTO.holder());
        Account account = accountMapper.toEntity(createRequestDTO,
                                                 holder);

        account = accountRepository.save(account);
        return accountMapper.toResponseDTO(account);
    }

    @Override
    public AccountResponseDTO deposit(UUID id, DepositRequestDTO depositRequestDTO) {
        Account account = findById(id);
        account.deposit(depositRequestDTO.amount());
        account = accountRepository.save(account);
        return accountMapper.toResponseDTO(account);
    }

    @Override
    public AccountResponseDTO withdrawal(UUID id, WithdrawalRequestDTO withdrawalRequestDTO) {
        Account account = findById(id);
        account.withdrawal(withdrawalRequestDTO.amount());
        account = accountRepository.save(account);
        return accountMapper.toResponseDTO(account);
    }

}