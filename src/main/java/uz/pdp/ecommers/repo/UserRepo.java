package uz.pdp.ecommers.repo;

import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommers.config.ConnectionPoolManager;
import uz.pdp.ecommers.entity.User;
import uz.pdp.ecommers.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class UserRepo {
    public static Optional<User> findByUsername(String username) {
        String query = "select * from users where username = ?";
        try (
                Connection connection =  ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (Optional.of(new User(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("photo"),
                        Role.valueOf(resultSet.getString("role"))
                )));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<User> findById(UUID userId) {
        String query = "select * from users where id =?";
        try (
                Connection connection =  ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setObject(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (Optional.of(new User(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("photo"),
                        Role.valueOf(resultSet.getString("role"))
                )));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static User getUser(HttpSession session) {
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser == null) {
            return null;
        }else {
            return (User) currentUser;
        }
    }
}
