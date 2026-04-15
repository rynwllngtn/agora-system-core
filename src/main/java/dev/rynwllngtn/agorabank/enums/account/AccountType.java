package dev.rynwllngtn.agorabank.enums.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    NONE(0, null),
    CHECKING(1, "Corrente"),
    SAVING(2, "Poupança");

    private final int id;
    private final String label;

}