package dev.rynwllngtn.agorasystem.entities.account;

import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.enums.account.AccountType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
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

    public Account(BigDecimal balance, BigDecimal transferLimit, BigDecimal transferLimitCap) {
        this.balance = balance;
        this.transferLimit = transferLimit;
        this.transferLimitCap = transferLimitCap;
    }

    public void update(BigDecimal balance, BigDecimal transferLimit, BigDecimal transferLimitCap) {
        this.balance = balance;
        this.transferLimit = transferLimit;
        this.transferLimitCap = transferLimitCap;
    }

}