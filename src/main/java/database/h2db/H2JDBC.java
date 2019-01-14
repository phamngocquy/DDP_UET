package database.h2db;

import config.ConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@PropertySource("classpath:application.properties")
@Component
public class H2JDBC {

//    @Value("${spring.datasource.driver-class-name}")
//    private String DB_DRIVER;
//
//    @Value("${spring.datasource.url}")
//    private String DB_CONNECTION;
//
//    @Value("${spring.datasource.username}")
//    private String DB_USER;
//
//    @Value("${spring.datasource.password}")
//    private String DB_PASSWORD;

    private static Connection con;

    private H2JDBC() {

    }

    private static H2JDBC h2JDBC;


    public static H2JDBC getH2JDBC() {
        if (h2JDBC == null) {
            h2JDBC = new H2JDBC();
        }
        return h2JDBC;
    }

    public Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(ConfigProperties.DB_CONNECTION, ConfigProperties.DB_USER, ConfigProperties.DB_PASSWORD);
            } catch (SQLException e) {
                Logger.getLogger(H2JDBC.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return con;
    }


}