package ru.otus.homework_16.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricController {

    private final MeterRegistry registry;

    @PostMapping("/customCounterMetric")
    public void customCounter() {
        registry.counter("custom.counter").increment();
    }
}
