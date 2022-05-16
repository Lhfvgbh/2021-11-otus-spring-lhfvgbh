package ru.otus.homework_18.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.homework_18.service.ActuatorServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AppHealthIndicator implements HealthIndicator {
    private final String url;
    private final ActuatorServiceImpl service;

    public AppHealthIndicator(@Value("${spring.datasource.url}") String url,
                              ActuatorServiceImpl service) {
        this.url = url;
        this.service = service;
    }

    @Override
    public Health health() {
        try {
            service.checkDBState(url, "sa", "");
            return Health.up().status(Status.UP).withDetail("message", "DB is available").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Health.down().status(Status.DOWN).withDetail("message", "DB is DOWN").build();
        }
    }
}
