package com.filepackage.service;

import com.filepackage.dto.InterestandValuesDto;
import com.filepackage.entity.InterestandValues;

public interface IInterestandValuesService <InterestandValuesDto,Long> extends IBaseService<InterestandValuesDto,Long>{
    InterestandValuesDto createInterestandValues(InterestandValuesDto commentDto);
}
