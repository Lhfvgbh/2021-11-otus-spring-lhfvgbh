package ru.otus.lhfvgbh.prototype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ImportResource("classpath:/integration/http-orders-integration.xml")
public class OrderIntegrationConfig {

    /*private DirectChannel ordersChannel;

    public OrderIntegrationConfig(@Qualifier("ordersChannel") DirectChannel ordersChannel) {
        this.ordersChannel = ordersChannel;
    }

    public DirectChannel getOrdersChannel(){
        return ordersChannel;
    }*/

}
