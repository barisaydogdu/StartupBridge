package com.filepackage.entity;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    public  String token;
    public AuthenticationResponse(String token)
    {
        this.token=token;
    }
}
