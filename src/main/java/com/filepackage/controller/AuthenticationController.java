package com.filepackage.controller;

import com.filepackage.entity.AuthenticationResponse;
import com.filepackage.entity.User;
import com.filepackage.service.impl.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthenticationController {

        private AuthenticationService authenticationService;

        public AuthenticationController(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }
        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody User request
        )
        {
            return ResponseEntity.ok(authenticationService.register(request));
        }
        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse>login(
                @RequestBody User request)
        {
            return ResponseEntity.ok(authenticationService.authenticationResponse(request));
        }

    }


