package com.codenrock.raifcib21.flashlight.controller;

import com.codenrock.raifcib21.flashlight.service.MessageToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MessageToUserController {

    private final MessageToUserService service;

    @MessageMapping("/init")
    public void init() {
        service.sendInitial();
    }
}
