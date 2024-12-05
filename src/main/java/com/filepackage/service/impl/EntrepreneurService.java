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
public class EntrepreneurService implements IEntrepreneurService {

    private final IEntrepreneurRepository entrepreneurRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public EntrepreneurService(IEntrepreneurRepository entrepreneurRepository, AutoMapper autoMapper) {
        this.entrepreneurRepository = entrepreneurRepository;
        this.autoMapper = autoMapper;
    }

    @Override
    public EntrepreneurDto getById(Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur not found with ID: " + entrepreneurId));
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
    public EntrepreneurDto createEntrepreneur(EntrepreneurDto entrepreneurDto) {
        Entrepreneur entrepreneur = autoMapper.convertToEntity(entrepreneurDto, Entrepreneur.class);
        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }

    @Override
    public EntrepreneurDto update(Long entrepreneurId, EntrepreneurDto updatedEntrepreneurDto) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur not found with ID: " + entrepreneurId));

        entrepreneur.setFirstName(updatedEntrepreneurDto.getFirstName());
        entrepreneur.setLastName(updatedEntrepreneurDto.getLastName());
        entrepreneur.setProfilePicture(updatedEntrepreneurDto.getProfilePicture());
        entrepreneur.setEmail(updatedEntrepreneurDto.getEmail());
        entrepreneur.setPassword(updatedEntrepreneurDto.getPassword());
        entrepreneur.setBio(updatedEntrepreneurDto.getBio());
        entrepreneur.setPhoneNumber(updatedEntrepreneurDto.getPhoneNumber());
        entrepreneur.setPhoneVisibility(updatedEntrepreneurDto.getPhoneVisibility());

        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }

    @Override
    public void delete(Long entrepreneurId) {
        if (!entrepreneurRepository.existsById(entrepreneurId)) {
            throw new ResourceNotFoundException("Entrepreneur not found with ID: " + entrepreneurId);
        }
        entrepreneurRepository.deleteById(entrepreneurId);
    }
}
