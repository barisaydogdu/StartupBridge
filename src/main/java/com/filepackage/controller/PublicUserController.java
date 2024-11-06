package com.filepackage.controller;

import com.filepackage.dto.PublicUsersDto;
import com.filepackage.dto.UserDto;
import com.filepackage.service.impl.PublicUserService;
import com.filepackage.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public_users")
public class PublicUserController {
    @Autowired
    private PublicUserService publicUserService;

    @GetMapping
    public ResponseEntity<List<PublicUsersDto>> getAllPublicUsers() {
        List<PublicUsersDto> users = publicUserService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<PublicUsersDto> addUser(@RequestBody PublicUsersDto userDto){
        PublicUsersDto savedUser = publicUserService.createPublicUser(userDto);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PublicUsersDto> getUserById(@PathVariable("id") Long userId) {
        PublicUsersDto userDto = publicUserService.getById(userId);
        return ResponseEntity.ok(userDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        publicUserService.delete(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PutMapping("{id}")
    public ResponseEntity<PublicUsersDto> updateUser (@PathVariable("id") Long userId, @RequestBody PublicUsersDto updatedUser) {
        PublicUsersDto userDto=publicUserService.update(userId,updatedUser);
        return ResponseEntity.ok(userDto);
    }
}
