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
@Table(name = "usersettings",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "setting_id")
    private Integer setting_id;

    @Column(name = "notification_preferences")
    private  String notification_preferences;

    @Column(name = "profile_visibility")
    private String profile_visibility;

    @Column(name = "favorite_projects")
    private String favorite_projects;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}
