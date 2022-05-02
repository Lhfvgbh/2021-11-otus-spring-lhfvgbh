package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Cloth;
import ru.otus.spring.domain.Clothes;

@Service
public class AtelierService {

    private static final int CLOTHES_COEFFICIENT = 114;

    public Clothes sew(Cloth cloth) throws InterruptedException {
        System.out.println("Start sewing " + cloth.getItemName() + " of " + cloth.getFabricType());
        Thread.sleep(5000);
        Clothes clothes = new Clothes(cloth.getItemName(), countSizeOfTheClothes(cloth.getArea()));
        System.out.println("Sewing " + clothes.getItemName() + " size " + clothes.getSize() + " is done");
        return clothes;
    }

    private int countSizeOfTheClothes(double clothArea) {
        return (int) (clothArea * CLOTHES_COEFFICIENT / Math.sqrt(CLOTHES_COEFFICIENT));
    }
}
