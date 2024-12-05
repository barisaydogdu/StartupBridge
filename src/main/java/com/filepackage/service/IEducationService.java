package com.filepackage.service;

import com.filepackage.dto.EducationDto;

import java.util.List;

public interface IEducationService {
    List<EducationDto> getAll();

    EducationDto createEducation(EducationDto educationDto);

    EducationDto getById(Long educationId);

    void delete(Long educationId);

    EducationDto update(Long educationId, EducationDto updatedEducationDto);
}
