package ru.otus.homework_6.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputServiceConsoleImpl implements OutputService {

    public void printOne(Object object) {
        System.out.println(object.toString());
    }

    public void printList(List<Object> list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }
}
