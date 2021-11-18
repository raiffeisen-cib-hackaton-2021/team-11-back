package com.codenrock.raifcib21.flashlight.controller;

import com.codenrock.raifcib21.flashlight.model.MessageFromUser;
import com.codenrock.raifcib21.flashlight.service.MessageFromUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/message-from-user")
@RequiredArgsConstructor
public class MessageFromUserController {

    private final MessageFromUserService service;

    @PostMapping
    public UUID post(@RequestBody MessageFromUser messageFromUser) {
        return service.persist(messageFromUser).getId();
    }
}
