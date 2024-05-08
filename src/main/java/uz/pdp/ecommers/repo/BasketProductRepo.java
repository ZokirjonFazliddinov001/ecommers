package uz.pdp.ecommers.repo;

import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommers.config.ConnectionPoolManager;
import uz.pdp.ecommers.entity.BasketProduct;
import uz.pdp.ecommers.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static uz.pdp.ecommers.repo.OrderProductRepo.getProduct;

public class BasketProductRepo {

    public static List<BasketProduct> findAll(){
        String query = "select * from basket_product";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            return getResultsetQuery(statement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Product getProductById(Integer productId) {
        return getProduct(productId);
    }

    public static void deleteById(int id) {
        String query = "delete from basket_product where id = ?";
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

    public static void deleteAll() {
        String query = "delete from basket_product where id>=1";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BasketProduct findById(int id) {
        String query = "select * from basket_product where id=?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            List<BasketProduct> basketProducts = getBasketProduct(id, statement);
            return basketProducts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }





    @SuppressWarnings("unchecked")
    public static List<BasketProduct> getNotLoginUserBasketProducts(HttpSession session) {
        Object basketObj = session.getAttribute("basket");

        //productId  amount
        Map<Integer, Integer> basketProductsMap = new HashMap<>();
        if (basketObj != null) {
            basketProductsMap = (Map<Integer, Integer>) basketObj;

            List<BasketProduct> basketProducts = new ArrayList<>();
            for (Map.Entry<Integer, Integer> basketProduct : basketProductsMap.entrySet()) {
                BasketProduct basketProduct1 = new BasketProduct();
                basketProduct1.setBasketId(-0);
                basketProduct1.setProductId(basketProduct.getKey());
                basketProduct1.setAmount(basketProduct.getValue());
                basketProducts.add(basketProduct1);
            }
            return basketProducts;
        } else {
            session.setAttribute("basket", basketProductsMap);
        }
        return new ArrayList<>();
    }


    public static List<BasketProduct> findAllByUserId(UUID uuid) {
        return null;
    }

    public static List<BasketProduct> findByBasketId(int basketId) {

        String query = "select * from basket_product where basket_id=?";
        try (
                Connection connection =   ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            return getBasketProduct(basketId, statement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<BasketProduct> getBasketProduct(int basketId, PreparedStatement statement) throws SQLException {
        statement.setInt(1, basketId);
        return getResultsetQuery(statement);
    }

    private static List<BasketProduct> getResultsetQuery(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<BasketProduct> basketProducts = new ArrayList<>();
        while (resultSet.next()) {
            basketProducts.add(new BasketProduct(
                    resultSet.getInt("id"),
                    resultSet.getInt("product_id"),
                    resultSet.getInt("amount"),
                    resultSet.getInt("basket_id")
            ));
        }
        return basketProducts;
    }

    public static void delete(Integer productId, int basketId) {

        String sql = "delete from basket_product where product_id = ? and basket_id = ?";
        try (
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, basketId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Map<Product, Integer> basket) {
        String query = "insert into basket_product(product_id, amount, basket_id) values (?, ?, 3)";
        try (
                Connection connection = ConnectionPoolManager.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
                preparedStatement.setInt(1, entry.getKey().getId());
                preparedStatement.setInt(2, entry.getValue());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
