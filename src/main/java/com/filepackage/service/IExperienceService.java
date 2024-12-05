package com.filepackage.service;

import com.filepackage.dto.ExperienceDto;
import com.filepackage.entity.Experience;

public interface IExperienceService <ExperienceDto,Long> extends IBaseService<ExperienceDto,Long>{
    ExperienceDto createExperience(ExperienceDto commentDto);
}
