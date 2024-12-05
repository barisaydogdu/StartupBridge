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
@Table(name = "investors",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Investors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investor_id")
    private Long investor_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private  String last_name;

    @Column(name = "profile_picture")
    private String profile_picture;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "phone_visibility")
    private String phone_visibility;

    @Column(name = "location")
    private String location;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}