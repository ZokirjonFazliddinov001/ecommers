package uz.pdp.ecommers.repo;
import uz.pdp.ecommers.config.ConnectionPoolManager;
import uz.pdp.ecommers.entity.BasketProduct;
import uz.pdp.ecommers.entity.Order;
import uz.pdp.ecommers.enums.Status;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepo {

    public static List<Order> findAll() {
        String query = "select * from orders";
        try (
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Timestamp timestamp = resultSet.getTimestamp("date_time");
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                Status status = Status.valueOf(resultSet.getString("status"));
                UUID userId = UUID.fromString(resultSet.getString("user_id"));
                int price = resultSet.getInt("price");
                orders.add(new Order(id, dateTime, status, userId, price ));
            }
            return orders;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Order createOrder(UUID userId, int sum) {
        String query = "insert into orders(user_id, status, price) values ( ?, 'OPEN', ?) returning *";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setObject(1, userId);
            statement.setInt(2, sum);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Order(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("date_time").toLocalDateTime(),
                    Status.valueOf(resultSet.getString("status")),
                    UUID.fromString(resultSet.getString("user_id")),
                    resultSet.getInt("price")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Order findById(int orderId) {
        String query = "select * from orders where id = ?";
        try (
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Timestamp timestamp = resultSet.getTimestamp("date_time");
                    LocalDateTime dateTime = timestamp.toLocalDateTime();
                    Status status = Status.valueOf(resultSet.getString("status"));
                    UUID userId = UUID.fromString(resultSet.getString("user_id"));
                    int price = resultSet.getInt("price");
                    return new Order(id, dateTime, status, userId, price);
                }
                return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Order makeOrder(int basketId, UUID userId, int sum) {
        Order order = createOrder(userId, sum);
        List<BasketProduct> basketProducts = BasketProductRepo.findByBasketId(basketId);

        for (BasketProduct basketProduct : basketProducts) {
            String sql = "insert into order_product values (?,?,?)";//amount product_id order_id
            try (
                    Connection connection = ConnectionPoolManager.dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                preparedStatement.setInt(1, basketProduct.getAmount());
                preparedStatement.setInt(2, basketProduct.getProductId());
                preparedStatement.setInt(3, order.getId() );

                BasketProductRepo.delete(basketProduct.getProductId(),basketProduct.getBasketId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return order;
    }

    public static void save(Order order) {
        String query = "insert into orders(user_id, status) values (?, ?)";
        try(
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void edit(Order order) {
        String query = "update orders set user_id = ?, status = ?, price = ? where id = ?";
        try(
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus().toString());
            preparedStatement.setInt(3, order.getPrice());
            preparedStatement.setInt(4, order.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
