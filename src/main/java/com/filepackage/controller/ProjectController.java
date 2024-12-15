package com.filepackage.controller;


import com.filepackage.dto.ProjectDto;
import com.filepackage.dto.UserDto;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.service.impl.EntrepreneurService;
import com.filepackage.service.impl.ProjectService;
import com.filepackage.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    private EntrepreneurService entrepreneurService;

    public ProjectController(ProjectService projectService, EntrepreneurService entrepreneurService) {
        this.projectService = projectService;
        this.entrepreneurService = entrepreneurService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAll();
        return ResponseEntity.ok(projects);
    }
    @PostMapping
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto){
        Entrepreneur entrepreneur = entrepreneurService.getEntrepreneurByAuthenticatedUser();
        //projectDto.setEntrepreneur_id(entrepreneur.getEntrepreneurId());

        projectDto.setEntrepreneur_id(entrepreneur.getEntrepreneurId());
        ProjectDto savedProject = projectService.createProject(projectDto);

        savedProject.setEntrepreneur_id(entrepreneur.getEntrepreneurId());
        return  new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long userId) {
        ProjectDto projectDto = projectService.getById(userId);
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject (@PathVariable("id") Long userId) {
        projectService.delete(userId);
        return ResponseEntity.ok("Project deleted successfully");
    }
    @PutMapping("{id}")
    public ResponseEntity<ProjectDto> updateProject (@PathVariable("id") Long userId, @RequestBody ProjectDto updatedProject) {
        ProjectDto projectDto=projectService.update(userId,updatedProject);
        return ResponseEntity.ok(projectDto);
    }

}
