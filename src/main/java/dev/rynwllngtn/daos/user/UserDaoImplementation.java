package dev.rynwllngtn.daos.user;

import dev.rynwllngtn.entities.user.User;
import dev.rynwllngtn.exceptions.database.DatabaseException;
import dev.rynwllngtn.utils.DatabaseUtil;
import dev.rynwllngtn.utils.UserUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoImplementation implements UserDao{

    private Connection connection;

    public UserDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User user) {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("""
                        INSERT INTO user
                        (Id, Cpf, Password, Name, Email, BirthDate, IsActive)
                        VALUES
                        (?, ?, ?, ?, ?, ?, ?)
                        """);

            statement.setString(1, user.getId().toString());
            statement.setString(2, user.getCpf());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setString(5, user.getEmail());
            statement.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
            statement.setBoolean(7, user.isActive());

            if (statement.executeUpdate() < 1) {
                throw new DatabaseException("ERRO: No row affected!");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            DatabaseUtil.closeStatement(statement);
        }
    }

    @Override
    public void update(User user) {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("""
                        UPDATE user SET
                        Cpf = ?, Password = ?, Name = ?, Email = ?, BirthDate = ?, IsActive = ?
                        WHERE Id = ?
                        """);

            statement.setString(1, user.getCpf());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            statement.setBoolean(6, user.isActive());
            statement.setString(7, user.getId().toString());

            if (statement.executeUpdate() < 1) {
                throw new DatabaseException("ERRO: No row affected!");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            DatabaseUtil.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(UUID id) {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("""
                        DELETE FROM user
                        WHERE id = ?;
                        """);

            statement.setString(1, id.toString());

            if (statement.executeUpdate() < 1) {
                throw new DatabaseException("ERRO: No row affected!");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            DatabaseUtil.closeStatement(statement);
        }
    }

    @Override
    public User findById(UUID id) {

        PreparedStatement statement = null;
        ResultSet resultSet = null ;

        try {
            statement = connection.prepareStatement("""
                        SELECT * FROM user
                        WHERE Id = ? 
                        """);

            statement.setString(1, id.toString());
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

        PreparedStatement statement = null;
        ResultSet resultSet = null ;

        try {
            statement = connection.prepareStatement("""
                        SELECT * FROM user
                        """);

            resultSet = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(UserUtil.instantiateUser(resultSet));
            }

            return users;
        }
        catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
        }
    }

}