package uz.pdp.ecommers.repo;

import uz.pdp.ecommers.config.ConnectionPoolManager;
import uz.pdp.ecommers.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepo {
    public static List<Category> findAll(){
        String query = "select * from category";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
            return categories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Category category) {
        String query = "insert into category(name) values (?)";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setString(1, category.getName());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(Integer id) {
        String query = "delete from category where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Category findById(int id) {
        String query = "select * from category where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Category(
                    resultSet.getInt("id"),
            resultSet.getString("name"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void edit(Category category) {

        String query = "update category set name = ? where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
