package dev.rynwllngtn.agorabank.dtos.user;

import java.time.LocalDate;

public record UserCreateRequestDTO(
        String cpf,
        String password,
        String userName,
        LocalDate birthDate
) {}