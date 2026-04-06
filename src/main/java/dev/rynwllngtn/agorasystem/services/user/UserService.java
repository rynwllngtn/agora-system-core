package dev.rynwllngtn.agorasystem.services.user;

import dev.rynwllngtn.agorasystem.dtos.user.UserCreateRequestDTO;
import dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorasystem.dtos.user.UserUpdateRequestDTO;
import dev.rynwllngtn.agorasystem.entities.user.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    UserResponseDTO findById(UUID id);

    User insert(UserCreateRequestDTO userCreateRequestDTO);

    void delete(UUID id);

    User update(UUID id, UserUpdateRequestDTO userUpdateRequestDTO);

    User findUserById(UUID id);

}