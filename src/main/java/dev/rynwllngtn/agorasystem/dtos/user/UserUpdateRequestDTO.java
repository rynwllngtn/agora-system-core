package dev.rynwllngtn.agorasystem.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequestDTO {

    private String password;
    private String userName;
    private LocalDate birthDate;
    private boolean active;

}