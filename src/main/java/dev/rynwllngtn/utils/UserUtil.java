package dev.rynwllngtn.utils;

import dev.rynwllngtn.entities.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserUtil {

    public static User instantiateUser(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(UUID.fromString(resultSet.getString("Id")));
        user.setCpf(resultSet.getString("Cpf"));
        user.setPassword(resultSet.getString("Password"));
        user.setName(resultSet.getString("Name"));
        user.setEmail(resultSet.getString("Email"));
        user.setBirthDate(resultSet.getDate("BirthDate"));
        user.setActive(resultSet.getBoolean("IsActive"));
        return user;
    }

}
