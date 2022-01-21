package ru.otus.springboothomework3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Slf4j
@Service
public class IOServiceImpl implements IOService {

    private final PrintStream outputStream;

    private final Scanner scanner;

    @Autowired
    public IOServiceImpl(@Value("#{ T(java.lang.System).in}") InputStream inputStream,
                         @Value("#{ T(java.lang.System).out}") PrintStream printStream) {
        this.outputStream = printStream;
        this.scanner = new Scanner(inputStream);
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
