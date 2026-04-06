package dev.rynwllngtn.agorasystem.entities.account.accounts;

import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CHECKING")
public class AccountChecking extends Account {

    public AccountChecking(BigDecimal balance, BigDecimal transferLimit, BigDecimal transferLimitCap) {
        super(balance, transferLimit, transferLimitCap);
        this.accountType = AccountType.CHECKING;
    }
}