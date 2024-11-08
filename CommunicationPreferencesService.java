package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.CommunicationPreferencesDto;
import com.filepackage.entity.CommunicationPreferences;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.ICommunicationPreferencesRepository;
import com.filepackage.service.ICommunicationPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunicationPreferencesService implements ICommunicationPreferencesService<CommunicationPreferencesDto, Long> {

    @Autowired
    private AutoMapper autoMapper;

    private final ICommunicationPreferencesRepository communicationPreferencesRepository;

    @Autowired
    public CommunicationPreferencesService(ICommunicationPreferencesRepository communicationPreferencesRepository) {
        this.communicationPreferencesRepository = communicationPreferencesRepository;
    }

    @Override
    public CommunicationPreferencesDto getById(Long preferenceId) {
        CommunicationPreferences preference = communicationPreferencesRepository.findById(preferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Preference does not exist with given id: " + preferenceId));
        return autoMapper.convertToDto(preference, CommunicationPreferencesDto.class);
    }

    @Override
    public List<CommunicationPreferencesDto> getAll() {
        List<CommunicationPreferences> preferences = communicationPreferencesRepository.findAll();
        return preferences.stream()
                .map(preference -> autoMapper.convertToDto(preference, CommunicationPreferencesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long preferenceId) {
        CommunicationPreferences preference = communicationPreferencesRepository.findById(preferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Preference does not exist with given id: " + preferenceId));
        communicationPreferencesRepository.deleteById(preferenceId);
    }

    @Override
    public CommunicationPreferencesDto update(Long preferenceId, CommunicationPreferencesDto updatedPreference) {
        CommunicationPreferences preference = communicationPreferencesRepository.findById(preferenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Preference does not exist with given id: " + preferenceId));

        preference.setInvestor_id(updatedPreference.getInvestor_id());
        preference.setMeeting_preference(updatedPreference.getMeeting_preference());
        preference.setContact_channels(updatedPreference.getContact_channels());

        CommunicationPreferences updatedEntity = communicationPreferencesRepository.save(preference);
        return autoMapper.convertToDto(updatedEntity, CommunicationPreferencesDto.class);
    }

    @Override
    public CommunicationPreferencesDto createPreference(CommunicationPreferencesDto preferenceDto) {
        CommunicationPreferences preference = autoMapper.convertToEntity(preferenceDto, CommunicationPreferences.class);
        CommunicationPreferences savedPreference = communicationPreferencesRepository.save(preference);
        return autoMapper.convertToDto(savedPreference, CommunicationPreferencesDto.class);
    }
}
