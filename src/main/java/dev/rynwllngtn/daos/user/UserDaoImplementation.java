package dev.rynwllngtn.daos.user;

import dev.rynwllngtn.entities.user.User;
import dev.rynwllngtn.exceptions.database.DatabaseException;
import dev.rynwllngtn.utils.DatabaseUtil;
import dev.rynwllngtn.utils.UserUtil;

import java.sql.*;
import java.util.List;

public class UserDaoImplementation implements UserDao{

    private Connection connection;

    public UserDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public User findById(String id) {

        PreparedStatement statement = null;
        ResultSet resultSet = null ;

        try {
            statement = connection.prepareStatement("""
                        SELECT * FROM user
                        WHERE Id = ? 
                        """);

            statement.setString(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = UserUtil.instantiateUser(resultSet);
                return user;
            }

            return null;
        }
        catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
        }
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

}