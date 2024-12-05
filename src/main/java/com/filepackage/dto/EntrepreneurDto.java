package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepreneurDto {

    private Long entrepreneurId;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String password;
    private String bio;
    private String phoneNumber;
    private Boolean phoneVisibility;
}
