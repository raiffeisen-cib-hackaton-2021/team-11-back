package com.codenrock.raifcib21.flashlight.service;

import com.codenrock.raifcib21.flashlight.configuration.WebSocketConfiguration;
import com.codenrock.raifcib21.flashlight.model.MessageToUser;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageToUserService {

    private static final String DESTINATION = WebSocketConfiguration.getDestinationPrefix();

    private final SimpMessagingTemplate template;

    public void send(MessageToUser message) {
        template.convertAndSend(DESTINATION, message);
    }
}
