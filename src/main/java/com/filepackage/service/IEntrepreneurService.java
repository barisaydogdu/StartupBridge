package com.filepackage.service;

import com.filepackage.dto.EntrepreneurDto;
import com.filepackage.entity.Entrepreneur;

import java.util.List;

public interface IEntrepreneurService {
    EntrepreneurDto getById(Long entrepreneurId);

    EntrepreneurDto createEntrepreneur(EntrepreneurDto entrepreneurDto);

    EntrepreneurDto update(Long entrepreneurId, EntrepreneurDto updatedEntrepreneur);

    void delete(Long entrepreneurId);

    List<EntrepreneurDto> getAll();
    Entrepreneur getEntrepreneurByAuthenticatedUser();
}
