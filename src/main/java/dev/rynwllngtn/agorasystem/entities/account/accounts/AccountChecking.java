package dev.rynwllngtn.agorasystem.entities.account.accounts;

import dev.rynwllngtn.agorasystem.dtos.account.AccountCreateRequestDTO;
import dev.rynwllngtn.agorasystem.entities.account.Account;
import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CHECKING")
public class AccountChecking extends Account {

    public AccountChecking(User holder) {
        super(holder);
        this.accountType = AccountType.CHECKING;
    }

    public AccountChecking(AccountCreateRequestDTO accountCreateRequestDTO) {
        super(accountCreateRequestDTO);
        this.accountType = AccountType.CHECKING;
    }
}