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
public class InvestorsDto {
    private Long investor_id;

    private String first_name;

    private  String last_name;

    private String profile_picture;

    private String email;

    private String password;

    private String bio;

    private String phone_number;

    private String phone_visibility;

    private String location;

    private LocalDateTime created_at;
}