package uz.pdp.ecommers.repo;

import uz.pdp.ecommers.config.ConnectionPoolManager;
import uz.pdp.ecommers.entity.Category;
import uz.pdp.ecommers.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    public static List<Product> findAll(){
        String query = "select * from product";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("category_id"),
                        resultSet.getBytes("photo")
                ));
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Product product) {
        String query = "insert into product(name, price, category_id, photo) values (?, ?, ?, ?)";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setBytes(4, product.getPhoto());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCategoryById(Integer categoryId) {
        String query = "select name from category where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Category category = new Category(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
            return category.getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Product findById(int id) {

        String query = "select * from product where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"), 
                    resultSet.getInt("price"),
                    resultSet.getInt("category_id"),
                    resultSet.getBytes("photo"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void edit(Product product) {
        String query = "update product set name = ?, price = ?, category_id = ? where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getCategoryId());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(int id) {
        String query = "delete from product where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> findByCategoryId(int categoryId) {
        String query = "select * from product where category_id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            resultSet.next();
            products.add(new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getInt("category_id"),
                    resultSet.getBytes("photo")));
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Product findPhotoById(int productId) {

        String query = "select photo from product where id = ?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return Product.builder().photo(resultSet.getBytes("photo")).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
