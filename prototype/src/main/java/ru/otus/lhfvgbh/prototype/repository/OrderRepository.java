package ru.otus.lhfvgbh.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lhfvgbh.prototype.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
