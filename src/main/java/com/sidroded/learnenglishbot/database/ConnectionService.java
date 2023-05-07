package com.sidroded.learnenglishbot.database;

import com.sidroded.learnenglishbot.bot.TelegramBot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionService {
    private final Connection connection = new DatabaseConnection().getConnection();

    public void addUser(String chatId, String name) {
        try {
            String selectQuery = "SELECT * FROM users WHERE chatId = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, chatId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                String insertQuery = "INSERT INTO users (chatId, name) VALUES (?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, chatId);
                insertStatement.setString(2, name);
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
