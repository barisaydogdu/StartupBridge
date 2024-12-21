package com.filepackage.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.*;

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
                .setAllowedOrigins("http://localhost:3000")  // CORS izinleri//
                // Eğer frontend başka bir portta çalışıyorsa
                .withSockJS();

    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Uygulama destinasyon prefix'i
     //   config.setApplicationDestinationPrefixes("/app");
        // Basit mesaj broker'ı etkinleştirme
       // config.enableSimpleBroker("/topic", "/queue");
        // Kullanıcı destinasyon prefix'i
    //    config.setUserDestinationPrefix("/user");
       // config.enableSimpleBroker("/topic");
      //  config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketAuthenticationInterceptor);
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(128 * 1024);
        registration.setSendBufferSizeLimit(512 * 1024);
        registration.setSendTimeLimit(20000);
    }
}