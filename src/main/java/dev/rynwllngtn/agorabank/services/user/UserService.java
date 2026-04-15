package dev.rynwllngtn.agorabank.services.user;

import dev.rynwllngtn.agorabank.dtos.user.UserCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorabank.entities.user.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {

    User findById(UUID id);
    User findReferenceById(UUID id);
    UserResponseDTO getResponseById(UUID id);

    UserResponseDTO insert(UserCreateRequestDTO createRequestDTO);

    UserResponseDTO deactivate(UUID id);
    UserResponseDTO reactivate(UUID id);

}