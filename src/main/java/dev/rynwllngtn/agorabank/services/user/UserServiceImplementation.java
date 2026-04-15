package dev.rynwllngtn.agorabank.services.user;

import dev.rynwllngtn.agorabank.dtos.user.UserCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorabank.entities.user.User;
import dev.rynwllngtn.agorabank.exceptions.database.DatabaseException.ResourceNotFoundException;
import dev.rynwllngtn.agorabank.mappers.user.UserMapper;
import dev.rynwllngtn.agorabank.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(
                () -> new ResourceNotFoundException(User.class, id));
    }

    @Override
    public User findReferenceById(UUID id) {
        try {
            return userRepository.getReferenceById(id);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(User.class, id);
        }
    }

    @Override
    public UserResponseDTO getResponseById(UUID id) {
        Optional<UserResponseDTO> userResponseDTO = userRepository.getResponseById(id);
        return userResponseDTO.orElseThrow(
                () -> new ResourceNotFoundException(User.class, id));
    }

    @Override
    public UserResponseDTO insert(UserCreateRequestDTO createRequestDTO) {
        User user = userMapper.toEntity(createRequestDTO);
        user = userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO deactivate(UUID id) {
        User user = findById(id);
        user.deactivate();
        user = userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO reactivate(UUID id) {
        User user = findById(id);
        user.reactivate();
        user = userRepository.save(user);
        return userMapper.toResponseDTO(user);
    }

}