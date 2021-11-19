package com.codenrock.raifcib21.flashlight.controller;

import com.codenrock.raifcib21.flashlight.model.MessageFromUser;
import com.codenrock.raifcib21.flashlight.model.MessageToUser;
import com.codenrock.raifcib21.flashlight.service.MessageFromUserService;
import com.codenrock.raifcib21.flashlight.service.MessageToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessagesController {

    private final MessageToUserService toService;

    private final MessageFromUserService fromService;

    @PostMapping
    public UUID post(@RequestBody MessageFromUser messageFromUser) {
        return fromService.persist(messageFromUser).getId();
    }

    @GetMapping
    public MessageToUser get() {
        throw new RuntimeException();
    }

    @MessageMapping("/init")
    public void init() {
        toService.sendInitial();
    }
}
