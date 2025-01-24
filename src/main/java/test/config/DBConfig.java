package test.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBConfig {

    private static final DataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/abb_tech");
        config.setSchema("student_service");
        config.setUsername("postgres");
        config.setPassword("postgres");
//        config.setMinimumIdle(2);
//        config.setMaximumPoolSize(5);
//        config.setConnectionTimeout(50000);
//        config.setIdleTimeout(30000);
        config.setAutoCommit(false);


        dataSource = new HikariDataSource(config);

    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}