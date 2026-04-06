package dev.rynwllngtn.agorasystem.entities.account.accounts;

import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@DiscriminatorValue("SAVING")
public class AccountSaving extends Account {

    public AccountSaving(BigDecimal balance, BigDecimal transferLimit, BigDecimal transferLimitCap) {
        super(balance, transferLimit, transferLimitCap);
        this.accountType = AccountType.SAVING;
    }

}