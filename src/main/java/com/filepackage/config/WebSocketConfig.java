package com.filepackage.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketAuthenticationInterceptor webSocketAuthenticationInterceptor;

    public WebSocketConfig(WebSocketAuthenticationInterceptor webSocketAuthenticationInterceptor) {
        this.webSocketAuthenticationInterceptor = webSocketAuthenticationInterceptor;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:8080","http://localhost:5500","http://127.0.0.1:5500")  // CORS izinleri//
                // Eğer frontend başka bir portta çalışıyorsa
                .withSockJS();

    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Uygulama destinasyon prefix'i
        config.setApplicationDestinationPrefixes("/app");
        // Basit mesaj broker'ı etkinleştirme
        config.enableSimpleBroker("/topic", "/queue");
        // Kullanıcı destinasyon prefix'i
        config.setUserDestinationPrefix("/user");
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketAuthenticationInterceptor);
    }


}