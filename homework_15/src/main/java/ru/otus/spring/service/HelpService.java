package ru.otus.spring.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Cloth;

import java.util.Random;

@Service
public class HelpService {
    private static final String[] ITEMS = {"coat", "jacket", "dress", "shirt", "pants", "hat", "skirt"};
    private static final String[] TYPES = {"cotton", "leather", "lace", "linen", "silk", "wool", "satin"};

    @Bean
    public QueueChannel clothChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel clothesChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow atelierFlow() {
        return IntegrationFlows.from("clothChannel")
                .split()
                .handle("atelierService", "sew")
                .aggregate()
                .channel("clothesChannel")
                .get();
    }

    public static Cloth generateCloth() {
        Cloth cloth = new Cloth(ITEMS[RandomUtils.nextInt(0, ITEMS.length)],
                TYPES[RandomUtils.nextInt(0, TYPES.length)],
                Math.round(3 + 7 * new Random().nextDouble()));
        System.out.println("New item to sew: " +
                cloth.getItemName() +
                " of " +
                cloth.getArea() +
                " meters " +
                cloth.getFabricType());
        return cloth;
    }
}
