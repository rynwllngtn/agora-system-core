package dev.rynwllngtn.agorasystem.dtos.account;

import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponseDTO {

    private BigDecimal balance;
    private BigDecimal transferLimit;
    private BigDecimal transferLimitCap;
    private AccountType accountType;

    public AccountResponseDTO(Account account) {
        balance = account.getBalance();
        transferLimit = account.getTransferLimit();
        transferLimitCap = account.getTransferLimitCap();
        accountType = account.getAccountType();
    }

}