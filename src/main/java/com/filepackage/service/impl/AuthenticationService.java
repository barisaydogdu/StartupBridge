package com.filepackage.service.impl;

import com.filepackage.entity.AuthenticationResponse;
import com.filepackage.entity.User;
import com.filepackage.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(IUserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        //  this.passwordEncoder = passwordEncoder;
        this.passwordEncoder=new BCryptPasswordEncoder();
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request)
    {
        Optional<User> existingUser=userRepository.findByName(request.getUsername());
        if (existingUser.isPresent())
        {
            User user= existingUser.get();
            String token=jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        }
        String encodedPassword=passwordEncoder.encode(request.getPassword());
        User user= new User();
        user.setName(request.getName());
        //user.get(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(request.getRole());

        user=userRepository.save(user);
        String token=jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse authenticationResponse(User request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user=userRepository.findByName(request.getName()).orElseThrow();
        String token=jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
