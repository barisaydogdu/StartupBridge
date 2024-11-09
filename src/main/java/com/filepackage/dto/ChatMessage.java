package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    
        private Long senderId;
        private Long receiverId;
        private String content;
        private String senderName; // Add this field


}
