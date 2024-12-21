package com.filepackage.config;

import com.filepackage.service.impl.JwtService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final JwtService jwtService;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public WebSocketHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String token = extractToken(session);
        // &&jwtService.validateToken(token))
        if (token != null) {
            String username = jwtService.extractUsername(token);
            sessions.put(username, session);
            System.out.println("WebSocket connection established for user: " + username);
        } else {
            System.out.println("Invalid token, closing connection");
            session.close();
        }
    }

    private String extractToken(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query != null && query.startsWith("token=")) {
            return query.substring(6);
        }
        return null;
    }
}