package dev.rynwllngtn.agorabank.mappers.user;

import dev.rynwllngtn.agorabank.dtos.user.UserCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorabank.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(),
                                   user.getUserName(),
                                   user.getBirthDate(),
                                   user.getStatus());
    }

    public User toEntity(UserCreateRequestDTO createRequestDTO) {
        return new User(createRequestDTO.cpf(),
                        createRequestDTO.password(),
                        createRequestDTO.userName(),
                        createRequestDTO.birthDate());
    }

}