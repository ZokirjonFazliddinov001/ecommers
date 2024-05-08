package uz.pdp.ecommers.repo;

import uz.pdp.ecommers.config.ConnectionPoolManager;
//import uz.pdp.ecommers.entity.Basket;
import uz.pdp.ecommers.entity.Basket;
import uz.pdp.ecommers.entity.BasketProduct;
import uz.pdp.ecommers.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketRepo {
//    public static List<Basket> findAll(){
//        String query = "select * from basket";
//        try (
//                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query);
//        ){
//            ResultSet resultSet = statement.executeQuery();
//            List<Basket> basketList = new ArrayList<>();
//            while (resultSet.next()) {
//                basketList.add(new Basket(
//                        resultSet.getInt("id"),
//                        Boolean.parseBoolean(resultSet.getString("status"))
//                ));
//            }
//            return basketList;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void saveBasketProduct(BasketProduct basketProduct) {
        String query = "insert into basket_product(product_id, amount, basket_id) values (?, ?, ?)";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setInt(1, basketProduct.getProductId());
            statement.setInt(2, basketProduct.getAmount());
            statement.setInt(3, basketProduct.getBasketId());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket findByUserId(UUID uuid) {
        String sql = "select * from basket where users_id = ?";
        try (
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Basket(
                    resultSet.getInt("id"),
                    UUID.fromString(resultSet.getString("users_id"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
