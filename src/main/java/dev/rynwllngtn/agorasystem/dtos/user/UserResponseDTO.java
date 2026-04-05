package dev.rynwllngtn.agorasystem.dtos.user;

import dev.rynwllngtn.agorasystem.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {

    private String userName;
    private LocalDate birthDate;
    private boolean active;

    public UserResponseDTO(User user) {
        userName = user.getUserName();
        birthDate = user.getBirthDate();
        active = user.isActive();
    }

}