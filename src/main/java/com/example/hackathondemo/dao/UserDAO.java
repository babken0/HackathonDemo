package com.example.hackathondemo.dao;

import com.example.hackathondemo.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/users?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "user1234";
    public int regUser(User user) throws ClassNotFoundException {
        String INSERT_USER_SQL = "INSERT INTO user" +
               " (firstname,lastname,age,username,password,confirmPassword) VALUES " +
                "(?,?,?,?,?,?)";

        Class.forName("com.mysql.cj.jdbc.Driver");
        int result = 0;

        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

                preparedStatement.setString(1, user.getFirstname());
                preparedStatement.setString(2, user.getLastname());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getUsername());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setString(6, user.getConfirmPassword());

                result = preparedStatement.executeUpdate();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
