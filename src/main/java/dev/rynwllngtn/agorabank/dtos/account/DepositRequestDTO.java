package dev.rynwllngtn.agorabank.dtos.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositRequestDTO(
        @Positive
        @NotNull
        BigDecimal amount
) {}