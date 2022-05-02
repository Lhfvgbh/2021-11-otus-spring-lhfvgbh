package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Cloth;
import ru.otus.spring.domain.Clothes;
import ru.otus.spring.integration.Atelier;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HelpService {

    private final Atelier atelier;

    private final String[] ITEMS = {"coat", "jacket", "dress", "shirt", "pants", "hat", "skirt"};
    private final String[] TYPES = {"cotton", "leather", "lace", "linen", "silk", "wool", "satin"};

    public Cloth generateCloth() {
        Cloth cloth = new Cloth(ITEMS[RandomUtils.nextInt(0, ITEMS.length)],
                TYPES[RandomUtils.nextInt(0, TYPES.length)],
                Math.round(1 + 4 * new Random().nextDouble()));
        System.out.println("New item to sew: " +
                cloth.getItemName() +
                " of " +
                cloth.getArea() +
                " meters " +
                cloth.getFabricType());
        return cloth;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void startAtelier() {
        Collection<Clothes> clothes = atelier.process(generateCloth());
        System.out.println("Ready item: " + clothes.stream()
                .map(Clothes::getItemName)
                .collect(Collectors.joining(",")));
    }
}
