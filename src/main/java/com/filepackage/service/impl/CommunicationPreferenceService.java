package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.CommunicationPreferenceDto;
import com.filepackage.entity.CommunicationPreference;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.ICommunicationPreferenceRepository;
import com.filepackage.service.ICommunicationPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunicationPreferenceService implements ICommunicationPreferenceService<CommunicationPreferenceDto, Long> {

    private final ICommunicationPreferenceRepository communicationPreferenceRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public CommunicationPreferenceService(ICommunicationPreferenceRepository communicationPreferenceRepository, AutoMapper autoMapper) {
        this.communicationPreferenceRepository = communicationPreferenceRepository;
        this.autoMapper = autoMapper;
    }

    @Override
    public CommunicationPreferenceDto getById(Long preferenceId) {
        CommunicationPreference preference = communicationPreferenceRepository.findById(preferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Communication Preference not found with ID: " + preferenceId));
        return autoMapper.convertToDto(preference, CommunicationPreferenceDto.class);
    }

    @Override
    public List<CommunicationPreferenceDto> getAll() {
        List<CommunicationPreference> preferencesList = communicationPreferenceRepository.findAll();
        return preferencesList.stream()
                .map(preference -> autoMapper.convertToDto(preference, CommunicationPreferenceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommunicationPreferenceDto createCommunicationPreference(CommunicationPreferenceDto preferenceDto) {
        CommunicationPreference preference = autoMapper.convertToEntity(preferenceDto, CommunicationPreference.class);
        CommunicationPreference savedPreference = communicationPreferenceRepository.save(preference);
        return autoMapper.convertToDto(savedPreference, CommunicationPreferenceDto.class);
    }

    @Override
    public CommunicationPreferenceDto update(Long preferenceId, CommunicationPreferenceDto updatedPreferenceDto) {
        CommunicationPreference preference = communicationPreferenceRepository.findById(preferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Communication Preference not found with ID: " + preferenceId));

        preference.setInvestorId(updatedPreferenceDto.getInvestorId());
        preference.setMeetingPreference(updatedPreferenceDto.getMeetingPreference());
        preference.setContactChannels(updatedPreferenceDto.getContactChannels());

        CommunicationPreference savedPreference = communicationPreferenceRepository.save(preference);
        return autoMapper.convertToDto(savedPreference, CommunicationPreferenceDto.class);
    }

    @Override
    public void delete(Long preferenceId) {
        communicationPreferenceRepository.findById(preferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Communication Preference not found with ID: " + preferenceId));
        communicationPreferenceRepository.deleteById(preferenceId);
    }
}
