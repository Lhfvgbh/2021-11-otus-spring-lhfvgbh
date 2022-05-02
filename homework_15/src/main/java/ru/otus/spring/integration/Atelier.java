package ru.otus.spring.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.domain.*;

import java.util.Collection;

@MessagingGateway
public interface Atelier {

    @Gateway(requestChannel = "clothChannel", replyChannel = "clothesChannel")
    Collection<Clothes> process(Cloth cloth);
}
