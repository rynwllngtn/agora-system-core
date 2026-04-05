package dev.rynwllngtn.agorasystem.dtos;

import dev.rynwllngtn.agorasystem.dtos.user.UserReferenceDTO;
import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountChecking;
import dev.rynwllngtn.agorasystem.entities.account.accounts.AccountSaving;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountRequest {

    protected UUID id;
    protected UserReferenceDTO holder;
    protected BigDecimal balance;
    protected BigDecimal transferLimit;
    protected BigDecimal transferLimitCap;
    protected AccountType accountType;

    public Account getAccount() {
        switch (accountType) {
            case SAVING -> {
                return new AccountSaving(this);
            }
            case CHECKING -> {
                return new AccountChecking(this);
            }
        }

        return null;
    }

}