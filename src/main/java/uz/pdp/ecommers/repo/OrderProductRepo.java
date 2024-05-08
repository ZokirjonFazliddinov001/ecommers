package uz.pdp.ecommers.repo;

import uz.pdp.ecommers.config.ConnectionPoolManager;
import uz.pdp.ecommers.entity.OrderProduct;
import uz.pdp.ecommers.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductRepo {
    public static List<OrderProduct> findAll(){
        String query = "select * from order_product";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            return getResultSet(statement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<OrderProduct> getResultSet(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<OrderProduct> orderProducts = new ArrayList<>();
        while (resultSet.next()) {
            orderProducts.add(new OrderProduct(
                    resultSet.getInt("amount"),
                    resultSet.getInt("product_id"),
                    resultSet.getInt("order_id")
            ));
        }
        return orderProducts;
    }

    public static Product getProductById(Integer productId) {
        return getProduct(productId);
    }

    static Product getProduct(Integer productId) {
        return ProductRepo.findById(productId);
    }


    public static OrderProduct findById(int id) {
        String query = "select * from order_product where order_id =? ";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<OrderProduct> orderProducts = new ArrayList<>();
            while (resultSet.next()) {
                orderProducts.add(new OrderProduct(
                        resultSet.getInt("amount"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("order_id")
                ));
            }
            return orderProducts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static List<OrderProduct> findByOrderId(Integer id) {
        String query = "select * from order_product where order_id = ?";
        try (
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            return getResultSet(statement);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    public static void save(OrderProduct orderProduct) {
        String query = "insert into order_product(amount, product_id, order_id) values (?, ?, ?)";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, orderProduct.getAmount());
            statement.setInt(2, orderProduct.getProductId());
            statement.setInt(3, orderProduct.getOrderId());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
