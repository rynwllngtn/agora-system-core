package dev.rynwllngtn.agorabank.entities.account;

import dev.rynwllngtn.agorabank.entities.user.User;
import dev.rynwllngtn.agorabank.enums.account.AccountType;
import dev.rynwllngtn.agorabank.exceptions.business.BusinessException.InsufficientFundsException;
import dev.rynwllngtn.agorabank.exceptions.business.BusinessException.InvalidAmountException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
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
    protected User holder;

    @Column(precision = 9, scale = 2, nullable = false)
    protected BigDecimal balance;

    @Column(name = "transfer_limit", precision = 9, scale = 2, nullable = false)
    protected BigDecimal transferLimit;

    @Column(name = "transfer_limit_cap", precision = 9, scale = 2, nullable = false)
    protected BigDecimal transferLimitCap;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", insertable = false, updatable = false, nullable = false)
    protected AccountType accountType;

    public Account(User holder, BigDecimal balance, BigDecimal transferLimit, BigDecimal transferLimitCap) {
        this.holder = holder;
        this.balance = balance;
        this.transferLimit = transferLimit;
        this.transferLimitCap = transferLimitCap;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException(amount.toString());
        }
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new InvalidAmountException(amount.toString());
        }

        balance = balance.add(amount);
    }

    public void withdrawal(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException(amount.toString());
        }
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new InvalidAmountException(amount.toString());
        }

        balance = balance.subtract(amount);
    }

}