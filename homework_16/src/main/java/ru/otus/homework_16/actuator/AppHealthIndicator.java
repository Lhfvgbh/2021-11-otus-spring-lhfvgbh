package ru.otus.homework_16.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class AppHealthIndicator implements HealthIndicator {
    @Value("${spring.datasource.url}")
    private final String url;

    @Override
    public Health health() {
        try {
            Connection connection = DriverManager.getConnection(url, "", "");
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM books limit 1");
            ps.executeQuery();
            return Health.up().status(Status.UP).withDetail("message", "DB is available").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Health.down().status(Status.DOWN).withDetail("message", "DB is DOWN").build();
        }
    }
}
