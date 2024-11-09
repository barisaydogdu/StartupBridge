package com.filepackage.service.impl;

import com.filepackage.entity.Message;
import com.filepackage.entity.User;
import com.filepackage.repository.IMessageRepository;
import com.filepackage.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MessageService {

    private final IMessageRepository messageRepository;

    public Message saveMessage(Message message) {
            return messageRepository.save(message);
        }
    public List<Message> getConversation(Long userId1, Long userId2) {
        return messageRepository.findConversation(userId1, userId2);
    }

        // İhtiyaç duyarsanız mesajları alma metodları ekleyebilirsiniz
    }

//    @Autowired
//    private IMessageRepository messageRepository;
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    public Message sendMessage(Long senderId, Long receiverId, String content) {
//        Optional<User> sender = userRepository.findById(senderId);
//        Optional<User> receiver = userRepository.findById(receiverId);
//
//        if (sender.isPresent() && receiver.isPresent()) {
//            Message message = new Message();
//            message.setSender(sender.get());
//            message.setReceiver(sender.get());
//            message.setContent(content);
//            return messageRepository.save(message);
//        }
//        throw new IllegalArgumentException("Geçersiz kullanıcı bilgileri.");
//    }
//
//    public List<Message> getMessages(Long userId1, Long userId2) {
//        Optional<User> user1 = userRepository.findById(userId1);
//        Optional<User> user2 = userRepository.findById(userId2);
//
//        if (user1.isPresent() && user2.isPresent()) {
//            return messageRepository.findBySenderAndReceiver(user1.get(), user2.get());
//        }
//        throw new IllegalArgumentException("Geçersiz kullanıcı bilgileri.");
//    }
