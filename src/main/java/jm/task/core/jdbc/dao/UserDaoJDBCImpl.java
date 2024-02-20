package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class UserDaoJDBCImpl implements UserDao {
    
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            // SQL statement to create the users table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT NOT NULL" +
                    ")";

            // Execute the SQL statement
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'users' created successfully.");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users")) {

            // Execute the DROP TABLE query
            preparedStatement.executeUpdate();

            System.out.println("Users table dropped successfully.");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            // Check if the table is empty
            if (isTableEmpty(connection)) {
                // If the table is empty, reset the auto-increment value for the 'id' column
                resetAutoIncrement(connection);
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {

                // Set parameters for the prepared statement
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);

                // Execute the insert query
                preparedStatement.executeUpdate();

                System.out.println("User saved successfully.");

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private boolean isTableEmpty(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM users LIMIT 1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        }
    }

    private void resetAutoIncrement(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("ALTER TABLE users AUTO_INCREMENT = 1")) {
            preparedStatement.executeUpdate();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

            // Set parameter for the prepared statement
            preparedStatement.setLong(1, id);

            // Execute the DELETE query to remove the user with the specified id
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User with id " + id + " removed successfully.");
            } else {
                System.out.println("No user found with id " + id + ".");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {

            // Execute the SELECT query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate over the result set and create User objects
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users")) {

            // Execute the DELETE query to remove all records from the table
            preparedStatement.executeUpdate();

            System.out.println("Users table cleaned successfully.");

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
    
}
