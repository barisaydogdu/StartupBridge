package com.filepackage.service;

import com.filepackage.dto.InvestorsDto;
import com.filepackage.entity.Investors;

public interface IInvestorsService <InvestorsDto,Long> extends IBaseService<InvestorsDto,Long>{
    InvestorsDto createInvestors(InvestorsDto commentDto);
}
