package dev.rynwllngtn.agorasystem.entities.account;

import dev.rynwllngtn.agorasystem.dtos.AccountRequest;
import dev.rynwllngtn.agorasystem.dtos.user.UserReferenceDTO;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    protected UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false)
    protected UserReferenceDTO holder;

    @Column(precision = 9, scale = 2, nullable = false)
    protected BigDecimal balance;

    @Column(name = "transfer_limit", precision = 9, scale = 2, nullable = false)
    protected BigDecimal transferLimit;

    @Column(name = "transfer_limit_cap", precision = 9, scale = 2, nullable = false)
    protected BigDecimal transferLimitCap;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", insertable = false, updatable = false, nullable = false)
    protected AccountType accountType;

    public Account(UserReferenceDTO holder) {
        this.holder = holder;
        balance = BigDecimal.ZERO;
        transferLimit = balance;
        transferLimitCap = transferLimit;
    }

    public Account(AccountRequest accountRequest) {
        holder = accountRequest.getHolder();
        balance = accountRequest.getBalance();
        transferLimit = accountRequest.getTransferLimit();
        transferLimitCap = accountRequest.getTransferLimitCap();
    }

}