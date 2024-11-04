package com.filepackage.controller;

import com.filepackage.dto.UserDto;
import com.filepackage.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
  private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> friendshipDtos = userService.getAllUsers();
        return ResponseEntity.ok(friendshipDtos);
    }
    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

}
