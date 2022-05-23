package ru.otus.lhfvgbh.prototype.service;

import ru.otus.lhfvgbh.prototype.domain.Product;
import ru.otus.lhfvgbh.prototype.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    void addToUserCart(Long productId, String username);

    void save(ProductDTO productDTO);

    void disable(Long productId);

    void enable(Long productId);

    ProductDTO findById(Long productId);
}
