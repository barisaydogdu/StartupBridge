package com.filepackage.config;

import org.springframework.messaging.Message;

import com.filepackage.service.impl.JwtService;
import com.filepackage.service.impl.UserDetailServiceImpl;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;
//compoonent
@Component
public class WebSocketAuthenticationInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailsService;

    public WebSocketAuthenticationInterceptor(JwtService jwtService, UserDetailServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
//test
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authToken = accessor.getFirstNativeHeader("Authorization");
            System.out.println("Auth Token: " + authToken);

            if (authToken != null && authToken.startsWith("Bearer ")) {
                String jwtToken = authToken.substring(7);
                String username = jwtService.extractUsername(jwtToken);
                System.out.println("Extracted Username: " + username);

                if (username != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtService.isValid(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        accessor.setUser(authentication);
                        accessor.getSessionAttributes().put("user", authentication);
                        System.out.println("User authenticated: " + username);
                        System.out.println("User set in accessor: " + accessor.getUser());
                    } else {
                        System.out.println("Invalid JWT token");
                    }
                } else {
                    System.out.println("Username extraction failed");
                }
            } else {
                System.out.println("Authorization header missing or does not start with Bearer");
            }
        } else {
            // Diğer mesajlarda, kullanıcıyı oturumdan geri yükleyin
            Principal user = accessor.getUser();
            if (user == null) {
                user = (Principal) accessor.getSessionAttributes().get("user");
                if (user != null) {
                    accessor.setUser(user);
                    System.out.println("User restored from session: " + user.getName());
                } else {
                    System.out.println("User not found in session attributes");
                }
            } else {
                System.out.println("User already set in accessor: " + user.getName());
            }
        }

        return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
    }
}

/*@Component
public class WebSocketAuthenticationInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailsService;

    public WebSocketAuthenticationInterceptor(JwtService jwtService, UserDetailServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // Check if the message is a CONNECT message
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authToken = accessor.getFirstNativeHeader("Authorization");
            if (authToken != null && authToken.startsWith("Bearer ")) {
                String jwtToken = authToken.substring(7);
                String username = jwtService.extractUsername(jwtToken);
                if (username != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtService.isValid(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        accessor.setUser(authentication);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        return message;
    }
}*/