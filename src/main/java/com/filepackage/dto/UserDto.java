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
public class   UserDto {
    private Long id; // Optional, if you need it for updates

    private String name;

    private String email;

    private String password;

    private String role; // Optional, for user roles like "USER", "ADMIN"

    private LocalDateTime createdAt; // This could be read-only in the DTO

}
