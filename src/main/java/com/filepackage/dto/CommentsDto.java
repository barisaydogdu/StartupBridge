package com.filepackage.dto;

import com.filepackage.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    /*private Long comment_id;

    private Long blog_id;
    private BlogDto blog;

    private  Long user_id;
    private UserDto userDto;

    private String comment_text;

    private LocalDateTime created_at;*/

    private Long commentId;  // Değiştirildi: comment_id -> commentId
    private Long blogId;     // Değiştirildi: blog_id -> blogId
    private Long userId;     // Değiştirildi: user_id -> userId
    private String commentText;  // Değiştirildi: comment_text -> commentText
    private LocalDateTime createdAt;  // Değiştirildi: created_at -> createdAt
    private UserDto user;
}
