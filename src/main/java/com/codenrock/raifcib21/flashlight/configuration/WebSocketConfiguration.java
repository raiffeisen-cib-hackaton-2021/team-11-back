package com.codenrock.raifcib21.flashlight.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private static final String DESTINATION_PREFIX = "/";
    public static final String APPLICATION_DESTINATION_PREFIX = "/";
    private static final String ENDPOINT = "/ws";
    private static final String[] ALLOWED_ORIGINS = new String[]{"*"};

    private final CustomHandshakeHandler handler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(DESTINATION_PREFIX);
        registry.setApplicationDestinationPrefixes(APPLICATION_DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ENDPOINT).setHandshakeHandler(handler).setAllowedOrigins(ALLOWED_ORIGINS).withSockJS();
        registry.addEndpoint(ENDPOINT).setHandshakeHandler(handler).setAllowedOrigins(ALLOWED_ORIGINS);
    }

    public static String getDestinationPrefix() {
        return DESTINATION_PREFIX;
    }
}
