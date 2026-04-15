package dev.rynwllngtn.agorabank.dtos.account;

import dev.rynwllngtn.agorabank.enums.account.AccountType;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountCreateRequestDTO(
        UUID holder,
        BigDecimal balance,
        BigDecimal transferLimit,
        BigDecimal transferLimitCap,
        AccountType accountType
) {}