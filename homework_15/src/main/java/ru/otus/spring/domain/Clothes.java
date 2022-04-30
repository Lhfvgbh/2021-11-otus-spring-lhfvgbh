package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Clothes {
    private final String itemName;
    private final int size;
}
