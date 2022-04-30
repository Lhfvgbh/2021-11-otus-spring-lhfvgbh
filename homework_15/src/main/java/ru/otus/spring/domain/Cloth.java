package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Cloth {
    private final String itemName;
    private final String fabricType;
    private final double area;
}
