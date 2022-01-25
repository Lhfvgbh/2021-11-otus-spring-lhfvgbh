package ru.otus.springboothomework3.services;

import org.springframework.stereotype.Service;

@Service
public class IOMessageServiceImpl implements IOMessageService {

    private final MessageService messageService;
    private final IOService ioService;

    public IOMessageServiceImpl(MessageService messageService, IOService ioService) {
        this.messageService = messageService;
        this.ioService = ioService;
    }

    public void printLine(String line, Object... args) {
        ioService.printLine(messageService.getMessage(line, args));
    }

    public String readLine() {
        return ioService.readLine();
    }
}
