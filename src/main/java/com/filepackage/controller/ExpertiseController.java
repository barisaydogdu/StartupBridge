package com.filepackage.controller;

import com.filepackage.dto.ExpertiseDto;
import com.filepackage.dto.ProjectDto;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.service.impl.EntrepreneurService;
import com.filepackage.service.impl.ExpertiseService;
import com.filepackage.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expertise")
public class ExpertiseController {
    @Autowired
    private ExpertiseService expertiseService;
    private EntrepreneurService entrepreneurService;


    public ExpertiseController(ExpertiseService expertiseService, EntrepreneurService entrepreneurService) {
        this.expertiseService = expertiseService;
        this.entrepreneurService = entrepreneurService;
    }

    @GetMapping
    public ResponseEntity<List<ExpertiseDto>> getAllProjects() {
        List<ExpertiseDto> projects = expertiseService.getAll();
        return ResponseEntity.ok(projects);
    }
    @PostMapping
    public ResponseEntity<ExpertiseDto> addProject(@RequestBody ExpertiseDto expertiseDto){
        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurByAuthenticatedUser();
        expertiseDto.setEntrepreneur_id(entrepreneur.getEntrepreneurId());
        ExpertiseDto savedProject = expertiseService.createExpertise(expertiseDto);
        savedProject.setEntrepreneur_id(entrepreneur.getEntrepreneurId());
        return  new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpertiseDto> getProjectById(@PathVariable("id") Long userId) {
        ExpertiseDto projectDto = expertiseService.getById(userId);
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject (@PathVariable("id") Long userId) {
        expertiseService.delete(userId);
        return ResponseEntity.ok("Project deleted successfully");
    }
    @PutMapping("{id}")
    public ResponseEntity<ExpertiseDto> updateProject (@PathVariable("id") Long userId, @RequestBody ExpertiseDto updatedProject) {
        ExpertiseDto projectDto=expertiseService.update(userId,updatedProject);
        return ResponseEntity.ok(projectDto);
    }
}
