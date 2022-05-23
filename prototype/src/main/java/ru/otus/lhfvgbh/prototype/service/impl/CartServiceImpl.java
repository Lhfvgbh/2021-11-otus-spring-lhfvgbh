package ru.otus.lhfvgbh.prototype.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lhfvgbh.prototype.domain.*;
import ru.otus.lhfvgbh.prototype.dto.CartDTO;
import ru.otus.lhfvgbh.prototype.dto.CartProductDetailDTO;
import ru.otus.lhfvgbh.prototype.repository.CartRepository;
import ru.otus.lhfvgbh.prototype.repository.ProductRepository;
import ru.otus.lhfvgbh.prototype.service.CartService;
import ru.otus.lhfvgbh.prototype.service.OrderService;
import ru.otus.lhfvgbh.prototype.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderService orderService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Cart createCart(User user, List<Long> products) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(getProductsById(products));
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void addProducts(Cart cart, List<Long> productIds) {
        List<Product> products = cart.getProducts();
        List<Product> updatedProducts = products == null ? new ArrayList<>() : new ArrayList<>(products);
        updatedProducts.addAll(getProductsById(productIds));
        cart.setProducts(updatedProducts);
        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void addProduct(Cart cart, Long product) {
        List<Product> products = cart.getProducts();
        List<Product> updatedProducts = products == null ? new ArrayList<>() : new ArrayList<>(products);
        updatedProducts.add(productRepository.findById(product).orElse(new Product()));
        cart.setProducts(updatedProducts);
        cartRepository.save(cart);
    }

    @Override
    public CartDTO getCartByUser(String username) {

        User user = userService.findByName(username);

        if (user == null || user.getCart() == null) {
            return new CartDTO();
        }

        CartDTO cartDTO = new CartDTO();
        Map<Long, CartProductDetailDTO> map = new HashMap<>();
        List<Product> products = user.getCart().getProducts();

        for (Product p : products) {
            CartProductDetailDTO detailDTO = map.get(p.getId());
            if (detailDTO == null) {
                map.put(p.getId(), new CartProductDetailDTO(p));
            } else {
                detailDTO.setProductAmount(detailDTO.getProductAmount().add(new BigDecimal("1.0")));
                detailDTO.setSum(detailDTO.getSum() + Double.parseDouble(p.getPrice().toString()));
            }
        }
        cartDTO.setCartDetails(new ArrayList<>(map.values()));
        cartDTO.aggregate();

        return cartDTO;
    }

    private List<Product> getProductsById(List<Long> ids) {
        return ids.stream()
                .map(productRepository::getById)
                .collect(Collectors.toList());
    }

    private Cart getCartId(String username) {
        User user = userService.findByName(username);
        return cartRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void deleteProductFromCart(String username, Long productId) {
        Cart cart = getCartId(username);
        Product p = productRepository.findById(productId).orElse(new Product());
        cart.removeProduct(p);
        entityManager.merge(cart);
        entityManager.flush();
    }

    @Transactional
    @Override
    public void orderCart(String username) {
        User user = userService.findByName(username);
        if(user == null){
            throw new RuntimeException("User is not found");
        }
        Cart cart = user.getCart();
        if(cart == null || cart.getProducts().isEmpty()){
            return;
        }

        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);

        Map<Product, Long> productWithAmount = cart.getProducts().stream()
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        List<OrderDetails> orderDetails = productWithAmount.entrySet().stream()
                .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());

        BigDecimal total = new BigDecimal(orderDetails.stream()
                .map(detail -> detail.getPrice().multiply(detail.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetails(orderDetails);
        order.setTotalPrice(total);

        orderService.saveOrder(order);
        cart.getProducts().clear();
        cartRepository.save(cart);
    }
}
