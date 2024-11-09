package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages",schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Mesajı gönderen kullanıcı
        @ManyToOne
        @JoinColumn(name = "sender_id")
        private User sender;

        // Mesajı alan kullanıcı
        @ManyToOne
        @JoinColumn(name = "receiver_id")
        private User receiver;

        @Column(name = "content")
        private String content;

        @Column(name = "timestamp")
        private LocalDateTime timestamp;

        @PrePersist
        protected void onCreate() {
                this.timestamp = LocalDateTime.now();
        }
}
