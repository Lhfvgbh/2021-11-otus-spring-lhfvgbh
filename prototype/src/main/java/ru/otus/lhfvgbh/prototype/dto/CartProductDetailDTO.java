package ru.otus.lhfvgbh.prototype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lhfvgbh.prototype.domain.Product;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartProductDetailDTO {
    private String productName;
    private Long productId;
    private BigDecimal productPrice;
    private BigDecimal productAmount;
    private Double sum;

    public CartProductDetailDTO(Product product) {
        this.productName = product.getName();
        this.productId = product.getId();
        this.productPrice = product.getPrice();
        this.productAmount = new BigDecimal("1.0");
        this.sum = Double.parseDouble(product.getPrice().toString());
    }
}
