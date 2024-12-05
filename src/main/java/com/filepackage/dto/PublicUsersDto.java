package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicUsersDto {
    private Long user_id;

    private String email;

    private String username;

    private String password;

    private String role;

    private LocalDateTime created_at;

}
