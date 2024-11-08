package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.EntrepreneurDto;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IEntrepreneurRepository;
import com.filepackage.service.IEntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrepreneurService implements IEntrepreneurService<EntrepreneurDto, Long> {

    @Autowired
    private AutoMapper autoMapper;

    private final IEntrepreneurRepository entrepreneurRepository;

    @Autowired
    public EntrepreneurService(IEntrepreneurRepository entrepreneurRepository) {
        this.entrepreneurRepository = entrepreneurRepository;
    }

    @Override
    public EntrepreneurDto getById(Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur does not exist with given id: " + entrepreneurId));
        return autoMapper.convertToDto(entrepreneur, EntrepreneurDto.class);
    }

    @Override
    public List<EntrepreneurDto> getAll() {
        List<Entrepreneur> entrepreneurs = entrepreneurRepository.findAll();
        return entrepreneurs.stream()
                .map(entrepreneur -> autoMapper.convertToDto(entrepreneur, EntrepreneurDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur does not exist with given id: " + entrepreneurId));
        entrepreneurRepository.deleteById(entrepreneurId);
    }

    @Override
    public EntrepreneurDto update(Long entrepreneurId, EntrepreneurDto updatedEntrepreneur) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur does not exist with given id: " + entrepreneurId));

        entrepreneur.setFirst_name(updatedEntrepreneur.getFirst_name());
        entrepreneur.setLast_name(updatedEntrepreneur.getLast_name());
        entrepreneur.setProfile_picture(updatedEntrepreneur.getProfile_picture());
        entrepreneur.setEmail(updatedEntrepreneur.getEmail());
        entrepreneur.setPassword(updatedEntrepreneur.getPassword());
        entrepreneur.setBio(updatedEntrepreneur.getBio());
        entrepreneur.setPhone_number(updatedEntrepreneur.getPhone_number());
        entrepreneur.setPhone_visibility(updatedEntrepreneur.isPhone_visibility());

        Entrepreneur updatedEntity = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(updatedEntity, EntrepreneurDto.class);
    }

    @Override
    public EntrepreneurDto createEntrepreneur(EntrepreneurDto entrepreneurDto) {
        Entrepreneur entrepreneur = autoMapper.convertToEntity(entrepreneurDto, Entrepreneur.class);
        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }
}
