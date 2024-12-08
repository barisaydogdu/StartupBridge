package com.filepackage.controller;

import org.springframework.messaging.Message;
import com.filepackage.entity.User;
import com.filepackage.dto.ChatMessage;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.impl.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final IUserRepository userRepository;
    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage, Message<?> message) {
        // Mesajın header'larından Principal nesnesini alın
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);

        if (principal == null) {
            System.out.println("Error: principal is null");
            return;
        }

        String senderName = principal.getName();
        if (senderName == null) {
            System.out.println("Error: senderName is null");
            return;
        }

        System.out.println("Sender Name: " + senderName);

        // Göndericiyi veritabanından alın
        User sender = userRepository.findByName(senderName).orElse(null);
        if (sender == null) {
            System.out.println("Error: Sender not found for name: " + senderName);
            return;
        }

        // Alıcı ID'sini mesajdan alın
        Long receiverId = chatMessage.getReceiverId();
        System.out.println("Receiver ID: " + receiverId);

        if (receiverId == null) {
            System.out.println("Error: receiverId is null");
            return;
        }

        // Alıcıyı veritabanından alın
        User receiver = userRepository.findById(receiverId).orElse(null);
        if (receiver == null) {
            System.out.println("Error: Receiver not found for ID: " + receiverId);
            return;
        }

        // Mesaj entity'sini oluşturun ve kaydedin
        com.filepackage.entity.Message messageEntity = new com.filepackage.entity.Message();
        messageEntity.setContent(chatMessage.getContent());
        messageEntity.setSender(sender);
        messageEntity.setReceiver(receiver);
        messageService.saveMessage(messageEntity);

        // Alıcıya gönderilecek mesajda gönderenin adını ayarlayın
        chatMessage.setSenderName(sender.getName());

        // Mesajı alıcıya gönderin
       /* messagingTemplate.convertAndSendToUser(
                receiver.getName(), "/queue/messages", chatMessage);*/
        // Mesajı alıcı ve gönderici için genel bir kanala gönder

        // Mesajı hem alıcıya hem göndericiye gönder
        messagingTemplate.convertAndSend("/topic/messages/" + receiver.getId(), chatMessage);
        messagingTemplate.convertAndSend("/topic/messages/" + sender.getId(), chatMessage);

    }
    @MessageMapping("/chat.privateMessage")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage, Principal principal) {
        String senderName = principal.getName();
        Long receiverId = chatMessage.getReceiverId();
        User sender = userRepository.findByName(senderName).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if (sender != null && receiver != null) {
           // String privateChannel = "/topic/messages/" + sender.getId() + "_" + receiver.getId();
            String privateChannel = "/topic/messages/9";

            // Veritabanına mesaj kaydetme
            com.filepackage.entity.Message messageEntity = new com.filepackage.entity.Message();
            messageEntity.setContent(chatMessage.getContent());
            messageEntity.setSender(sender);
            messageEntity.setReceiver(receiver);
            messageService.saveMessage(messageEntity);

            // Her iki kullanıcıya mesaj gönderme
            messagingTemplate.convertAndSend(privateChannel, chatMessage);
        }
    }


}