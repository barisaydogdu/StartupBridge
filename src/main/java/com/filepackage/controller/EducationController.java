package com.filepackage.controller;

import com.filepackage.dto.EducationDto;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.service.impl.EducationService;
import com.filepackage.service.impl.EntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {


    @Autowired
    private final EducationService educationService;
    private EntrepreneurService entrepreneurService;

    public EducationController(EducationService educationService,EntrepreneurService entrepreneurService) {
        this.educationService = educationService;
        this.entrepreneurService = entrepreneurService;
    }

    @GetMapping
    public ResponseEntity<List<EducationDto>> getAllEducations() {
        List<EducationDto> educations = educationService.getAll();
        return ResponseEntity.ok(educations);
    }

    @PostMapping
    public ResponseEntity<EducationDto> addEducation(@RequestBody EducationDto educationDto) {

        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurByAuthenticatedUser();
        educationDto.setEntrepreneurId(entrepreneur.getEntrepreneurId());
        EducationDto savedEducation = educationService.createEducation(educationDto);
        savedEducation.setEntrepreneurId(entrepreneur.getEntrepreneurId());
        return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDto> getEducationById(@PathVariable("id") Long educationId) {
        EducationDto educationDto = educationService.getById(educationId);
        return ResponseEntity.ok(educationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable("id") Long educationId) {
        educationService.delete(educationId);
        return ResponseEntity.ok("Education deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationDto> updateEducation(@PathVariable("id") Long educationId, @RequestBody EducationDto updatedEducation) {
        EducationDto educationDto = educationService.update(educationId, updatedEducation);
        return ResponseEntity.ok(educationDto);
    }
}
