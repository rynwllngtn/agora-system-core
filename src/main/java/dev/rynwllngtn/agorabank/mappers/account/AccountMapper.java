package dev.rynwllngtn.agorabank.mappers.account;

import dev.rynwllngtn.agorabank.dtos.account.AccountCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorabank.entities.account.Account;
import dev.rynwllngtn.agorabank.entities.account.accounts.AccountChecking;
import dev.rynwllngtn.agorabank.entities.account.accounts.AccountSaving;
import dev.rynwllngtn.agorabank.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDTO toResponseDTO(Account account) {
        return new AccountResponseDTO(account.getId(),
                                      account.getBalance(),
                                      account.getTransferLimit(),
                                      account.getTransferLimitCap(),
                                      account.getAccountType());
    }

    public Account toEntity(AccountCreateRequestDTO createRequestDTO, User holder) {
        return switch (createRequestDTO.accountType()) {
            case CHECKING -> new AccountChecking(holder,
                                                 createRequestDTO.balance(),
                                                 createRequestDTO.transferLimit(),
                                                 createRequestDTO.transferLimitCap());

            case SAVING -> new AccountSaving(holder,
                                             createRequestDTO.balance(),
                                             createRequestDTO.transferLimit(),
                                             createRequestDTO.transferLimitCap());

            case NONE -> null;
        };
    }

}