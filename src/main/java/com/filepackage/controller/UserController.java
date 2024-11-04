package com.filepackage.controller;

import com.filepackage.dto.UserDto;
import com.filepackage.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
       UserDto savedUser = userService.addUser(userDto);
       return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getById(userId);
        return ResponseEntity.ok(userDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser (@PathVariable("id") Long userId, @RequestBody UserDto updatedUser) {
        UserDto userDto=userService.updateUser(userId,updatedUser);
        return ResponseEntity.ok(userDto);
    }

}
