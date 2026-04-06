package dev.rynwllngtn.agorasystem.services.user;

import dev.rynwllngtn.agorasystem.dtos.user.UserCreateRequestDTO;
import dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorasystem.dtos.user.UserUpdateRequestDTO;
import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.exceptions.database.DatabaseException.ResourceNotFoundException;
import dev.rynwllngtn.agorasystem.exceptions.database.DatabaseException.UserConstraintException;
import dev.rynwllngtn.agorasystem.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO findById(UUID id) {
        Optional<UserResponseDTO> user = userRepository.findUserById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public User insert(UserCreateRequestDTO userCreateRequestDTO) {
        User user = new User(userCreateRequestDTO.getCpf(),
                             userCreateRequestDTO.getPassword(),
                             userCreateRequestDTO.getUserName(),
                             userCreateRequestDTO.getBirthDate());

        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }

        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new UserConstraintException(e.getMessage());
        }
    }

    @Override
    public User update(UUID id, UserUpdateRequestDTO userUpdateRequestDTO) {

        try {
            User user = userRepository.getReferenceById(id);
            user.update(userUpdateRequestDTO.getPassword(),
                        userUpdateRequestDTO.getUserName(),
                        userUpdateRequestDTO.getBirthDate(),
                        user.isActive());

            return userRepository.save(user);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

}