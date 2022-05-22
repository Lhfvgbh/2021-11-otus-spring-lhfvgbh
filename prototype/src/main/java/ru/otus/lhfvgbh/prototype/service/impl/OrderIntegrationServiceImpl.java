package ru.otus.lhfvgbh.prototype.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lhfvgbh.prototype.config.OrderIntegrationConfig;
import ru.otus.lhfvgbh.prototype.domain.Order;
import ru.otus.lhfvgbh.prototype.dto.OrderIntegrationDTO;
import ru.otus.lhfvgbh.prototype.repository.OrderRepository;
import ru.otus.lhfvgbh.prototype.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class OrderIntegrationServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderIntegrationConfig integrationConfig;

    public OrderIntegrationServiceImpl(OrderRepository orderRepository, OrderIntegrationConfig integrationConfig) {
        this.orderRepository = orderRepository;
        this.integrationConfig = integrationConfig;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        sendIntegrationNotify(savedOrder);
    }

    private void sendIntegrationNotify(Order order) {
        OrderIntegrationDTO dto = new OrderIntegrationDTO();
        dto.setUsername(order.getUser().getName());
        dto.setOrderId(order.getId());
        List<OrderIntegrationDTO.OrderDetailsDTO> details = order.getDetails().stream()
                .map(OrderIntegrationDTO.OrderDetailsDTO::new).collect(Collectors.toList());
        dto.setDetails(details);

        /*Message<OrderIntegrationDTO> message = MessageBuilder.withPayload(dto)
                .setHeader("Content-type", "application/json")
                .build();

        integrationConfig.getOrdersChannel().send(message);*/
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
