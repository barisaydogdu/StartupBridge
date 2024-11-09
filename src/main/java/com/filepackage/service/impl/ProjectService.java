package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.ProjectDto;
import com.filepackage.dto.UserDto;
import com.filepackage.entity.Project;
import com.filepackage.entity.User;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IProjectRepository;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService  implements IProjectService<ProjectDto,Long>{
    @Autowired
    AutoMapper autoMapper;
    private IProjectRepository projectRepository;

    @Autowired
    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository= projectRepository;
    }

    @Override
    public ProjectDto getById(Long projectId) {
        Project project= projectRepository.findById(projectId)
                .orElseThrow(()-> new ResourceNotFoundException("Project does not exist with given id)"+projectId));
        return autoMapper.convertToDto(project, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getAll() {
        List<Project> projects=projectRepository.findAll();

        return projects.stream().map(project -> autoMapper.convertToDto(project,ProjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long projectId) {
        Project project = projectRepository.findById(projectId).
                orElseThrow(()-> new ResourceNotFoundException("Project does not exist with given id"));
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectDto update(Long projectId, ProjectDto updatedProject) {
        Project project= projectRepository.findById(projectId).orElseThrow(() ->
                new ResourceNotFoundException("Project is not exist with given id:)" + projectId));
        project.setEntrepreneur_id(updatedProject.getEntrepreneur_id());
        project.setProject_name(updatedProject.getProject_name());
        project.setShort_description(updatedProject.getShort_description());
        project.setTarget_sector(updatedProject.getTarget_sector());
        project.setStage(updatedProject.getStage());
        project.setBudget_needed(updatedProject.getBudget_needed());
        project.setRevenue_model(updatedProject.getRevenue_model());
        Project projectObj= projectRepository.save(project);
        return autoMapper.convertToDto(updatedProject,ProjectDto.class);
    }


    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        //önce entitye sonra tekrardan dtoya çeviriyoruz
        Project project = autoMapper.convertToEntity(projectDto,Project.class);
        Project savedProject = projectRepository.save(project);
        return  autoMapper.convertToDto(savedProject,ProjectDto.class);
    }
}
