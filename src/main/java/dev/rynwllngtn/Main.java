package dev.rynwllngtn;

import dev.rynwllngtn.db.Db;
import dev.rynwllngtn.db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    static void main() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Db.getConnection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM seller " +
                        "WHERE Id = ?");

            preparedStatement.setInt(1, 8);

            int rows = preparedStatement.executeUpdate();
            IO.println(rows + " linhas alteradas!");
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            Db.closeStatement(preparedStatement);
            Db.closeConnection();
        }
    }

}