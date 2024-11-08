package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.EducationDto;
import com.filepackage.entity.Education;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IEducationRepository;
import com.filepackage.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService implements IEducationService<EducationDto, Long> {
    
    @Autowired
    private AutoMapper autoMapper;
    
    private final IEducationRepository educationRepository;

    @Autowired
    public EducationService(IEducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public EducationDto getById(Long educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education does not exist with given id: " + educationId));
        return autoMapper.convertToDto(education, EducationDto.class);
    }

    @Override
    public List<EducationDto> getAll() {
        List<Education> educations = educationRepository.findAll();
        return educations.stream()
                .map(education -> autoMapper.convertToDto(education, EducationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education does not exist with given id: " + educationId));
        educationRepository.deleteById(educationId);
    }

    @Override
    public EducationDto update(Long educationId, EducationDto updatedEducation) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education does not exist with given id: " + educationId));
        
        education.setEntrepreneur_id(updatedEducation.getEntrepreneur_id());
        education.setSchool_name(updatedEducation.getSchool_name());
        education.setDegree(updatedEducation.getDegree());
        education.setGraduation_year(updatedEducation.getGraduation_year());

        Education updatedEntity = educationRepository.save(education);
        return autoMapper.convertToDto(updatedEntity, EducationDto.class);
    }

    @Override
    public EducationDto createEducation(EducationDto educationDto) {
        Education education = autoMapper.convertToEntity(educationDto, Education.class);
        Education savedEducation = educationRepository.save(education);
        return autoMapper.convertToDto(savedEducation, EducationDto.class);
    }
}
