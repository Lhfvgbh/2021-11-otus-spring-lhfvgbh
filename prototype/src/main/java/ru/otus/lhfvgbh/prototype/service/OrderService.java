package ru.otus.lhfvgbh.prototype.service;

import ru.otus.lhfvgbh.prototype.domain.Order;

public interface OrderService {
    void saveOrder(Order order);

    Order getOrder(Long id);
}
