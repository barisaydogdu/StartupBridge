package com.filepackage.controller;

import com.filepackage.dto.CommunicationPreferencesDto;
import com.filepackage.service.impl.CommunicationPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communication-preferences")
public class CommunicationPreferencesController {

    @Autowired
    private CommunicationPreferencesService communicationPreferencesService;

    
    @GetMapping
    public ResponseEntity<List<CommunicationPreferencesDto>> getAllPreferences() {
        List<CommunicationPreferencesDto> preferences = communicationPreferencesService.getAll();
        return ResponseEntity.ok(preferences);
    }

    
    @PostMapping
    public ResponseEntity<CommunicationPreferencesDto> addPreference(@RequestBody CommunicationPreferencesDto communicationPreferencesDto) {
        CommunicationPreferencesDto savedPreference = communicationPreferencesService.createPreference(communicationPreferencesDto);
        return new ResponseEntity<>(savedPreference, HttpStatus.CREATED);
    }

    
    @GetMapping("{id}")
    public ResponseEntity<CommunicationPreferencesDto> getPreferenceById(@PathVariable("id") Long preferenceId) {
        CommunicationPreferencesDto communicationPreferencesDto = communicationPreferencesService.getById(preferenceId);
        return ResponseEntity.ok(communicationPreferencesDto);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePreference(@PathVariable("id") Long preferenceId) {
        communicationPreferencesService.delete(preferenceId);
        return ResponseEntity.ok("Communication preference deleted successfully");
    }

    
    @PutMapping("{id}")
    public ResponseEntity<CommunicationPreferencesDto> updatePreference(@PathVariable("id") Long preferenceId, @RequestBody CommunicationPreferencesDto updatedPreference) {
        CommunicationPreferencesDto communicationPreferencesDto = communicationPreferencesService.update(preferenceId, updatedPreference);
        return ResponseEntity.ok(communicationPreferencesDto);
    }
}
