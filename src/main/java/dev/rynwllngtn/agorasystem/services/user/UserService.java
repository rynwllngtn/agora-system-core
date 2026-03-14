package dev.rynwllngtn.agorasystem.services.user;

import dev.rynwllngtn.agorasystem.entities.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    public List<User> findAll();

    public User findById(UUID id);

    public User insert(User user);

    public void delete(UUID id);

}