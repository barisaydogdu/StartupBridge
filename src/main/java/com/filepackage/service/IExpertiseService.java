package com.filepackage.service;

import com.filepackage.dto.ExpertiseDto;

public interface IExpertiseService  <ExpertiseDto,Long> extends  IBaseService<ExpertiseDto,Long>{
    ExpertiseDto createExpertise(ExpertiseDto expertiseDto);

}
