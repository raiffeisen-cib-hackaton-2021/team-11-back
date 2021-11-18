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

    public static final String DESTINATION = "/topic";
    public static final String USER_DESTINATION = "/user";
    private static final String APPLICATION_DESTINATION_PREFIX = "/app";
    private static final String ENDPOINT = "/ws";
    private static final String[] ALLOWED_ORIGINS = new String[]{"*"};

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(DESTINATION);
        registry.setApplicationDestinationPrefixes(APPLICATION_DESTINATION_PREFIX);
        registry.setUserDestinationPrefix(USER_DESTINATION);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ENDPOINT).setAllowedOrigins(ALLOWED_ORIGINS).withSockJS();
        registry.addEndpoint(ENDPOINT).setAllowedOrigins(ALLOWED_ORIGINS);
    }
}
