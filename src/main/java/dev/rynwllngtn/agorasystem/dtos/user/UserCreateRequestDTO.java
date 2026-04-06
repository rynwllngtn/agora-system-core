package dev.rynwllngtn.agorasystem.dtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequestDTO {

    private String cpf;
    private String password;
    private String userName;
    private LocalDate birthDate;

}