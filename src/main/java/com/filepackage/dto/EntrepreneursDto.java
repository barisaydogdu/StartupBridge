package com.filepackage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepreneursDto {
    private Long entrepreneur_id;

    private String first_name;

    private String last_name;

    private String profile_picture;

    private String email;

    private String password;

    private String bio;

    private String phone_number;

    private Boolean phone_visibility;
}
