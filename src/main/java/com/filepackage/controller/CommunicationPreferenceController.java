package com.filepackage.controller;

import com.filepackage.dto.CommunicationPreferenceDto;
import com.filepackage.service.impl.CommunicationPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communication-preference")
public class CommunicationPreferenceController {

    private final CommunicationPreferenceService communicationPreferenceService;

    @Autowired
    public CommunicationPreferenceController(CommunicationPreferenceService communicationPreferenceService) {
        this.communicationPreferenceService = communicationPreferenceService;
    }

    @GetMapping
    public ResponseEntity<List<CommunicationPreferenceDto>> getAllPreferences() {
        List<CommunicationPreferenceDto> preferences = communicationPreferenceService.getAll();
        return ResponseEntity.ok(preferences);
    }

    @PostMapping
    public ResponseEntity<CommunicationPreferenceDto> addPreference(@RequestBody CommunicationPreferenceDto preferenceDto) {
        CommunicationPreferenceDto savedPreference = communicationPreferenceService.createCommunicationPreference(preferenceDto);
        return new ResponseEntity<>(savedPreference, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunicationPreferenceDto> getPreferenceById(@PathVariable("id") Long preferenceId) {
        CommunicationPreferenceDto preferenceDto = communicationPreferenceService.getById(preferenceId);
        return ResponseEntity.ok(preferenceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePreference(@PathVariable("id") Long preferenceId) {
        communicationPreferenceService.delete(preferenceId);
        return ResponseEntity.ok("Communication preference deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunicationPreferenceDto> updatePreference(
            @PathVariable("id") Long preferenceId,
            @RequestBody CommunicationPreferenceDto updatedPreferenceDto) {
        CommunicationPreferenceDto updatedPreference = communicationPreferenceService.update(preferenceId, updatedPreferenceDto);
        return ResponseEntity.ok(updatedPreference);
    }
}
