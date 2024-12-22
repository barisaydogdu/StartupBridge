package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
  //  private Long comments_id;
    private Long commentId;  // Değiştirildi: comments_id -> commentId


    @Column(name = "blog_id")
   // private Integer blog_id;
    private Long blogId;  // Değiştirildi: blog_id -> blogId



    @Column(name = "user_id")
    private  Long user_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

    @Column(name = "comment_text")
    //private String comment_text;
    private String commentText;  // Değiştirildi: comment_text -> commentText

    @Column(name = "created_at")
  //  private LocalDateTime created_at;
    private LocalDateTime createdAt;  // Değiştirildi: created_at -> createdAt

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}