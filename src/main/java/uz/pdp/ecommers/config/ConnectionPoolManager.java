package uz.pdp.ecommers.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionPoolManager {
    public static DataSource dataSource;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/ecommerce");
            config.setUsername("postgres");
            config.setPassword("root123");
            config.setMinimumIdle(10);
            config.setMaximumPoolSize(20);
            config.setConnectionTimeout(30000);
            dataSource = new HikariDataSource(config);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
