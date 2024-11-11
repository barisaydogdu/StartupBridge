package com.filepackage.service;

import com.filepackage.dto.EducationDto;

public interface IEducationService<EducationDto, Long> extends IBaseService<EducationDto, Long> {
    EducationDto createEducation(EducationDto educationDto);
}
