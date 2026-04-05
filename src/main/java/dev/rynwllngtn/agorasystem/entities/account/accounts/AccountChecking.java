package dev.rynwllngtn.agorasystem.entities.account.accounts;

import dev.rynwllngtn.agorasystem.dtos.AccountRequest;
import dev.rynwllngtn.agorasystem.dtos.user.UserReferenceDTO;
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

    public AccountChecking(UserReferenceDTO holder) {
        super(holder);
        this.accountType = AccountType.CHECKING;
    }

    public AccountChecking(AccountRequest accountRequest) {
        super(accountRequest);
        this.accountType = AccountType.CHECKING;
    }
}