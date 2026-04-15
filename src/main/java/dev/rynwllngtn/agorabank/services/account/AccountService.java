package dev.rynwllngtn.agorabank.services.account;

import dev.rynwllngtn.agorabank.dtos.account.*;
import dev.rynwllngtn.agorabank.entities.account.Account;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface AccountService {

    Account findById(UUID id);
    AccountResponseDTO getResponseById(UUID id);

    AccountResponseDTO insert(AccountCreateRequestDTO createRequestDTO);

    AccountResponseDTO deposit(UUID id, DepositRequestDTO depositRequestDTO);
    AccountResponseDTO withdrawal(UUID id, WithdrawalRequestDTO withdrawalRequestDTO);

}