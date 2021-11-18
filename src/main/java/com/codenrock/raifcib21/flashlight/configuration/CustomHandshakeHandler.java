package com.codenrock.raifcib21.flashlight.configuration;

import com.codenrock.raifcib21.flashlight.service.MessageToUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        return new StompPrincipal(UUID.randomUUID().toString());
    }

    @RequiredArgsConstructor
    private class StompPrincipal implements Principal {

        @Getter
        private final String name;
    }
}
