package com.filepackage.service;

import com.filepackage.dto.EntrepreneurDto;

public interface IEntrepreneurService extends IBaseService<EntrepreneurDto, Long> {
    EntrepreneurDto createEntrepreneur(EntrepreneurDto entrepreneurDto);
    EntrepreneurDto updateEntrepreneur(Long entrepreneurId, EntrepreneurDto entrepreneurDto);
      EntrepreneurDto getEntrepreneurById(Long entrepreneurId);
    void deleteEntrepreneur(Long entrepreneurId);
}
