package dev.rynwllngtn.agorabank.entities.account.accounts;

import dev.rynwllngtn.agorabank.entities.account.Account;
import dev.rynwllngtn.agorabank.entities.user.User;
import dev.rynwllngtn.agorabank.enums.account.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@DiscriminatorValue("SAVING")
public class AccountSaving extends Account {

    public AccountSaving(User holder, BigDecimal balance, BigDecimal transferLimit, BigDecimal transferLimitCap) {
        super(holder, balance, transferLimit, transferLimitCap);
        this.accountType = AccountType.SAVING;
    }

}