package com.filepackage.controller;

import com.filepackage.dto.ExperienceDto;
import com.filepackage.service.impl.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;


    @GetMapping
    public ResponseEntity<List<ExperienceDto>> getAllExperiences() {
        List<ExperienceDto> experiences = experienceService.getAll();
        return ResponseEntity.ok(experiences);
    }


    @PostMapping
    public ResponseEntity<ExperienceDto> addExperience(@RequestBody ExperienceDto experienceDto) {
        ExperienceDto savedExperience = experienceService.createExperience(experienceDto);
        return new ResponseEntity<>(savedExperience, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDto> getExperienceById(@PathVariable("id") Long experienceId) {
        ExperienceDto experienceDto = experienceService.getById(experienceId);
        return ResponseEntity.ok(experienceDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExperience(@PathVariable("id") Long experienceId) {
        experienceService.delete(experienceId);
        return ResponseEntity.ok("Experience deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDto> updateExperience(@PathVariable("id") Long experienceId, @RequestBody ExperienceDto updatedExperience) {
        ExperienceDto experienceDto = experienceService.update(experienceId, updatedExperience);
        return ResponseEntity.ok(experienceDto);
    }
}
