package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Slf4j
@Service
public class IOServiceImpl implements IOService {

    private final PrintStream outputStream;

    private final Scanner scanner;

    @Autowired
    public IOServiceImpl() {
        this.outputStream = System.out;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void printLine(String line) {
        outputStream.println(line);
    }


}
