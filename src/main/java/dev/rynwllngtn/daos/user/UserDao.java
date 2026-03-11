package dev.rynwllngtn.daos.user;

import dev.rynwllngtn.entities.user.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    void insert(User user);
    void update(User user);
    void deleteById(UUID id);
    User findById(UUID id);
    List<User> findAll();

}