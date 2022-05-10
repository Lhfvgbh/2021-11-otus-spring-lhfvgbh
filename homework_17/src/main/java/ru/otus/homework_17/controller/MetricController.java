package ru.otus.homework_17.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.BaseUnits;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {

    private final MeterRegistry registry;

    public MetricController(MeterRegistry registry) {
        this.registry = registry;

        Counter.builder("custom.counter")
                .baseUnit(BaseUnits.EVENTS)
                .description("The number of 'customCounterMetric' method calls")
                .register(registry);
    }

    @PostMapping("/customCounterMetric")
    public void customCounter() {
        registry.counter("custom.counter").increment();
    }
}
