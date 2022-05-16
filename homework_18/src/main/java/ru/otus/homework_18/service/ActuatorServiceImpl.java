package ru.otus.homework_18.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class ActuatorServiceImpl {

    public void checkDBState(String dbUrl, String dbUser, String dbPass) throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        PreparedStatement ps = connection.prepareStatement("SELECT id FROM books limit 1");
        ps.executeQuery();
    }
}
