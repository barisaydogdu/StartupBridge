package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.ExperienceDto;
import com.filepackage.entity.Experience;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IExperienceRepository;
import com.filepackage.service.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceService implements IExperienceService<ExperienceDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IExperienceRepository experienceRepository;

    @Autowired
    public ExperienceService(IExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public ExperienceDto getById(Long experienceId) {
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience does not exist with given id: " + experienceId));
        return autoMapper.convertToDto(experience, ExperienceDto.class);
    }

    @Override
    public List<ExperienceDto> getAll() {
        List<Experience> experiences = experienceRepository.findAll();
        return experiences.stream()
                .map(experience -> autoMapper.convertToDto(experience, ExperienceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long experienceId) {
        experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience does not exist with given id: " + experienceId));
        experienceRepository.deleteById(experienceId);
    }

    @Override
    public ExperienceDto update(Long experienceId, ExperienceDto updatedExperience) {
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience does not exist with given id: " + experienceId));

        experience.setCompany_name(updatedExperience.getCompany_name());
        experience.setExperience_id(updatedExperience.getExperience_id());
        experience.setDescription(updatedExperience.getExperience_id());
        experience.setPosition(updatedExperience.getPosition());
        experience.setEnterpreneur_id(updatedExperience.getEntrepreneur_id());
        experience.setDuration_years(updatedExperience.getDuration_years());

        Experience updatedEntity = experienceRepository.save(experience);
        return autoMapper.convertToDto(updatedEntity, ExperienceDto.class);
    }

    @Override
    public ExperienceDto createExperience(ExperienceDto experienceDto) {
        Experience experience = autoMapper.convertToEntity(experienceDto, Experience.class);
        Experience savedExperience = experienceRepository.save(experience);
        return autoMapper.convertToDto(savedExperience, ExperienceDto.class);
    }
}
