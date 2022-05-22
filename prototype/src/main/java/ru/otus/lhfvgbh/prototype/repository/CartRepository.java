package ru.otus.lhfvgbh.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lhfvgbh.prototype.domain.Cart;
import ru.otus.lhfvgbh.prototype.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
    Cart findByUserId(Long userId);
}
