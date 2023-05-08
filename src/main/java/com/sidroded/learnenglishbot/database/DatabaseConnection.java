package com.sidroded.learnenglishbot.database;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Configuration
@Component
public class DatabaseConnection {
    private static DatabaseConnection databaseConnectionInstance = null;
    private Connection connection;
    @Value("${bot.url}")
    String url;
    @Value("${bot.login}")
    String username;
    @Value("${bot.password}")
    String password;

    public void setDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance()
    {
        if (databaseConnectionInstance == null)
            databaseConnectionInstance = new DatabaseConnection();


        return databaseConnectionInstance;
    }

    public Connection getConnection() {
        setDatabaseConnection();
        return connection;
    }
}