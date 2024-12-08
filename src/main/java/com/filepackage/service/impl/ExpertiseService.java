package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.ExpertiseDto;
import com.filepackage.dto.ProjectDto;
import com.filepackage.entity.Expertise;
import com.filepackage.entity.Project;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IExpertiseRepository;
import com.filepackage.service.IExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpertiseService implements IExpertiseService<ExpertiseDto,Long> {
    @Autowired
    AutoMapper autoMapper;

    private IExpertiseRepository expertiseRepository;

    @Autowired
    public ExpertiseService(IExpertiseRepository expertiseRepository) {
        this.expertiseRepository= expertiseRepository;
    }

    @Override
    public ExpertiseDto getById(Long projectId) {
        Expertise project= expertiseRepository.findById(projectId)
                .orElseThrow(()-> new ResourceNotFoundException("Project does not exist with given id)"+projectId));
        return autoMapper.convertToDto(project, ExpertiseDto.class);
    }

    @Override
    public List<ExpertiseDto> getAll() {
        List<Expertise> projects=expertiseRepository.findAll();

        return projects.stream().map(project -> autoMapper.convertToDto(project,ExpertiseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long projectId) {
        Expertise project = expertiseRepository.findById(projectId).
                orElseThrow(()-> new ResourceNotFoundException("Project does not exist with given id"));
        expertiseRepository.deleteById(projectId);
    }

    @Override
    public ExpertiseDto update(Long projectId, ExpertiseDto updatedProject) {
        Expertise project= expertiseRepository.findById(projectId).orElseThrow(() ->
                new ResourceNotFoundException("Project is not exist with given id:)" + projectId));
        project.setEntrepreneur_id(updatedProject.getEntrepreneur_id());
        project.setExpertise_id(updatedProject.getExpertise_id());
        project.setSkill_name(updatedProject.getSkill_name());
        Expertise projectObj= expertiseRepository.save(project);
        return autoMapper.convertToDto(updatedProject,ExpertiseDto.class);
    }


    @Override
    public ExpertiseDto createExpertise(ExpertiseDto projectDto) {
        //önce entitye sonra tekrardan dtoya çeviriyoruz
        Expertise project = autoMapper.convertToEntity(projectDto,Expertise.class);
        Expertise savedProject = expertiseRepository.save(project);
        return  autoMapper.convertToDto(savedProject,ExpertiseDto.class);
    }


}
