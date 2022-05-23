package ru.otus.lhfvgbh.prototype.service;

import ru.otus.lhfvgbh.prototype.domain.Cart;
import ru.otus.lhfvgbh.prototype.domain.User;
import ru.otus.lhfvgbh.prototype.dto.CartDTO;

import java.util.List;

public interface CartService {

    Cart createCart(User user, List<Long> products);

    void addProducts(Cart cart, List<Long> products);

    void addProduct(Cart cart, Long product);

    CartDTO getCartByUser(String username);

    void deleteProductFromCart(String username, Long productId);

    void orderCart(String username);

}
