package ru.otus.lhfvgbh.prototype.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartDTO {
    private Long id;
    private Integer amountOfProducts;
    private Double totalSum;
    private List<CartProductDetailDTO> cartDetails = new ArrayList<>();

    public void aggregate() {
        this.amountOfProducts = cartDetails.size();
        this.totalSum = cartDetails.stream()
                .map(CartProductDetailDTO::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
