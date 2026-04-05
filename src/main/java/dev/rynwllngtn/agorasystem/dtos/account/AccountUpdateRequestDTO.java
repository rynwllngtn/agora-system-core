package dev.rynwllngtn.agorasystem.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountUpdateRequestDTO {

    private BigDecimal balance;
    private BigDecimal transferLimit;
    private BigDecimal TransferLimitCap;

}