package ru.otus.lhfvgbh.prototype.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lhfvgbh.prototype.domain.Product;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .isAvailable(product.getIsAvailable())
                .build();
    }

    public Product fromDTO(){
        return Product.builder()
                .id(this.getId())
                .name(this.getName())
                .price(this.getPrice())
                .isAvailable(this.getIsAvailable())
                .description(this.getDescription())
                .build();
    }
}
