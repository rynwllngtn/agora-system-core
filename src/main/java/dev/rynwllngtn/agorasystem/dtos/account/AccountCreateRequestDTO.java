package dev.rynwllngtn.agorasystem.dtos.account;

import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountChecking;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountSaving;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AccountCreateRequestDTO {

    private UUID holder;
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private BigDecimal transferLimitCap;
    private AccountType accountType;

    public Account getAccount() {
        return switch (accountType) {
            case CHECKING -> new AccountChecking(this);
            case SAVING -> new AccountSaving(this);
            case NONE -> null;
        };
    }

}