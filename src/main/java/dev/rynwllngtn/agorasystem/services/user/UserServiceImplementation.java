package dev.rynwllngtn.agorasystem.services.user;

import dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO;
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
    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public User insert(User user) {
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
    public User update(UUID id, User userData) {

        try {
            User user = userRepository.getReferenceById(id);
            updateData(user, userData);
            return userRepository.save(user);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public UserResponseDTO findUserById(UUID id) {
        return userRepository.findUserById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private void updateData(User user, User userData) {
        user.setPassword(userData.getPassword());
        user.setUserName(userData.getUserName());
        user.setBirthDate(userData.getBirthDate());
        user.setActive(userData.isActive());
    }

}