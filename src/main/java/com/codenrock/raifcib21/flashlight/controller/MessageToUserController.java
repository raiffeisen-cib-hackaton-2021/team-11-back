package com.codenrock.raifcib21.flashlight.controller;

import com.codenrock.raifcib21.flashlight.model.MessageToUser;
import com.codenrock.raifcib21.flashlight.service.MessageToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import static com.codenrock.raifcib21.flashlight.configuration.WebSocketConfiguration.APPLICATION_DESTINATION_PREFIX;

@Controller
@RequiredArgsConstructor
public class MessageToUserController {

    private final MessageToUserService service;

    @MessageMapping(APPLICATION_DESTINATION_PREFIX + "init")
    public void init(Principal principal) {
        service.sendInitial(principal.getName());
    }
}
