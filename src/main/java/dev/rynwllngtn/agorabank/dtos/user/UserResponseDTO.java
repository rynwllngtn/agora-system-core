package dev.rynwllngtn.agorabank.dtos.user;

import dev.rynwllngtn.agorabank.enums.user.UserStatus;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String userName,
        LocalDate birthDate,
        UserStatus status
) {}