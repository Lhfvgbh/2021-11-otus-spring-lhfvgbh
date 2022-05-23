package ru.otus.lhfvgbh.prototype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lhfvgbh.prototype.domain.OrderDetails;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderIntegrationDTO {
    private Long orderId;
    private String username;
    private String address;
    private List<OrderDetailsDTO> details;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class OrderDetailsDTO {
        private String product;
        private Double price;
        private Double amount;
        private Double sum;

        public OrderDetailsDTO(OrderDetails details) {
            this.product = details.getProduct().getName();
            this.price = details.getPrice().doubleValue();
            this.amount = details.getAmount().doubleValue();
            this.sum = details.getPrice().multiply(details.getAmount()).doubleValue();
        }
    }
}
