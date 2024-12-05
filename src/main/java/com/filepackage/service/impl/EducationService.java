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
public class EducationService implements IEducationService {

    private final IEducationRepository educationRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public EducationService(IEducationRepository educationRepository, AutoMapper autoMapper) {
        this.educationRepository = educationRepository;
        this.autoMapper = autoMapper;
    }

    @Override
    public List<EducationDto> getAll() {
        List<Education> educations = educationRepository.findAll();
        return educations.stream()
                .map(education -> autoMapper.convertToDto(education, EducationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EducationDto createEducation(EducationDto educationDto) {
        Education education = autoMapper.convertToEntity(educationDto, Education.class);
        Education savedEducation = educationRepository.save(education);
        return autoMapper.convertToDto(savedEducation, EducationDto.class);
    }

    @Override
    public EducationDto getById(Long educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with ID: " + educationId));
        return autoMapper.convertToDto(education, EducationDto.class);
    }

    @Override
    public void delete(Long educationId) {
        if (!educationRepository.existsById(educationId)) {
            throw new ResourceNotFoundException("Education not found with ID: " + educationId);
        }
        educationRepository.deleteById(educationId);
    }

    @Override
    public EducationDto update(Long educationId, EducationDto updatedEducationDto) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with ID: " + educationId));

        education.setSchoolName(updatedEducationDto.getSchoolName());
        education.setDegree(updatedEducationDto.getDegree());
        education.setGraduationYear(updatedEducationDto.getGraduationYear());

        Education savedEducation = educationRepository.save(education);
        return autoMapper.convertToDto(savedEducation, EducationDto.class);
    }
}
