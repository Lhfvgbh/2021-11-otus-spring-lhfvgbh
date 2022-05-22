package ru.otus.lhfvgbh.prototype.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lhfvgbh.prototype.domain.Cart;
import ru.otus.lhfvgbh.prototype.domain.Product;
import ru.otus.lhfvgbh.prototype.domain.Role;
import ru.otus.lhfvgbh.prototype.domain.User;
import ru.otus.lhfvgbh.prototype.dto.CartDTO;
import ru.otus.lhfvgbh.prototype.dto.CartProductDetailDTO;
import ru.otus.lhfvgbh.prototype.dto.ProductDTO;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.mapper.ProductMapper;
import ru.otus.lhfvgbh.prototype.repository.ProductRepository;
import ru.otus.lhfvgbh.prototype.service.CartService;
import ru.otus.lhfvgbh.prototype.service.ProductService;
import ru.otus.lhfvgbh.prototype.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CartService cartService;
    private final UserService userService;

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::toDTO)
                .collect(Collectors.toList());
        //return mapper.fromProductList(productRepository.findAll());
    }

    @Transactional
    @Override
    public void addToUserCart(Long productId, String username) {
        User user = userService.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with name '" + username + "' not found!");
        }

        Cart cart = user.getCart();
        if (cart == null) {
            cart = cartService.createCart(user, Collections.singletonList(productId));
            user.setCart(cart);
            userService.save(user);
        } else {
            cartService.addProduct(cart, productId);
        }
    }

    @Transactional
    @Override
    public void save(ProductDTO productDTO) {
        Product product = productDTO.fromDTO();
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void disable(Long productId) {
        productRepository.disable(productId);
    }

    @Transactional
    @Override
    public void enable(Long productId) {
        productRepository.enable(productId);
    }

    @Override
    public ProductDTO findById(Long productId) {
        return ProductDTO.toDTO(productRepository.findById(productId).orElse(new Product()));
    }

}
