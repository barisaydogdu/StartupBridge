package com.filepackage.controller;

import com.filepackage.dto.MessageDto;
import com.filepackage.entity.Message;
import com.filepackage.entity.User;
import com.filepackage.repository.IMessageRepository;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IMessageRepository messageRepository;

    // WebSocket endpoint
    @MessageMapping("/send")
    @SendTo("/topic/messages/{userId}")
    public Message sendMessage(@Payload MessageDto messageDTO) {
        return processMessage(messageDTO);
    }

    // REST endpoint
    @PostMapping("/send")
    public Message sendMessageq(@RequestBody MessageDto messageDTO) {
        return processMessage(messageDTO);
    }

    private Message processMessage(MessageDto messageDTO) {
        Long senderId = messageDTO.getSenderId();
        Long receiverId = messageDTO.getReceiverId();
        String content = messageDTO.getContent();

        // Validate sender and receiver
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID."));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid receiver ID."));

        // Create and save the message
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        // Log for debugging
        System.out.println("Sender ID: " + senderId + ", Receiver ID: " + receiverId);

        // Save and return the message
        return messageRepository.save(message);
    }

    // Other methods...
}