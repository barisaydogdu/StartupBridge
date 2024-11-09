package com.filepackage.controller;

import com.filepackage.dto.UserSettingsDto;
import com.filepackage.service.impl.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersettings")
public class UserSettingsController {

    @Autowired
    private UserSettingsService userSettingsService;


    @GetMapping
    public ResponseEntity<List<UserSettingsDto>> getAllUserSettings() {
        List<UserSettingsDto> userSettings = userSettingsService.getAll();
        return ResponseEntity.ok(userSettings);
    }


    @PostMapping
    public ResponseEntity<UserSettingsDto> addUserSettings(@RequestBody UserSettingsDto userSettingsDto) {
        UserSettingsDto savedUserSettings = userSettingsService.createUserSettings(userSettingsDto);
        return new ResponseEntity<>(savedUserSettings, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserSettingsDto> getUserSettingsById(@PathVariable("id") Long userSettingsId) {
        UserSettingsDto userSettingsDto = userSettingsService.getById(userSettingsId);
        return ResponseEntity.ok(userSettingsDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserSettings(@PathVariable("id") Long userSettingsId) {
        userSettingsService.delete(userSettingsId);
        return ResponseEntity.ok("User Settings deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserSettingsDto> updateUserSettings(@PathVariable("id") Long userSettingsId, @RequestBody UserSettingsDto updatedUserSettings) {
        UserSettingsDto userSettingsDto = userSettingsService.update(userSettingsId, updatedUserSettings);
        return ResponseEntity.ok(userSettingsDto);
    }
}
