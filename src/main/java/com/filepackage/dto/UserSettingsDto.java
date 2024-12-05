package com.filepackage.dto;


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
public class UserSettingsDto {
    private Integer setting_id;

    private Long user_id;

    private String notification_preferences;

    private String profile_visibility;

    private String favorite_projects;

    private LocalDateTime created_at;
}