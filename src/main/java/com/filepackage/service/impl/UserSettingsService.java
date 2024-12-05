package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.UserSettingsDto;
import com.filepackage.entity.UserSettings;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IUserSettingsRepository;
import com.filepackage.service.IUserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSettingsService implements IUserSettingsService<UserSettingsDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IUserSettingsRepository userSettingsRepository;

    @Autowired
    public UserSettingsService(IUserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }

    @Override
    public UserSettingsDto getById(Long userSettingsId) {
        UserSettings userSettings = userSettingsRepository.findById(userSettingsId)
                .orElseThrow(() -> new ResourceNotFoundException("UserSettings does not exist with given id: " + userSettingsId));
        return autoMapper.convertToDto(userSettings, UserSettingsDto.class);
    }

    @Override
    public List<UserSettingsDto> getAll() {
        List<UserSettings> userSettingsList = userSettingsRepository.findAll();
        return userSettingsList.stream()
                .map(userSettings -> autoMapper.convertToDto(userSettings, UserSettingsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userSettingsId) {
        userSettingsRepository.findById(userSettingsId)
                .orElseThrow(() -> new ResourceNotFoundException("UserSettings does not exist with given id: " + userSettingsId));
        userSettingsRepository.deleteById(userSettingsId);
    }

    @Override
    public UserSettingsDto update(Long userSettingsId, UserSettingsDto updatedUserSettings) {
        UserSettings userSettings = userSettingsRepository.findById(userSettingsId)
                .orElseThrow(() -> new ResourceNotFoundException("UserSettings does not exist with given id: " + userSettingsId));

        userSettings.setSetting_id(updatedUserSettings.getSetting_id());
        userSettings.setUser_id(updatedUserSettings.getUser_id());
        userSettings.setCreated_at(updatedUserSettings.getCreated_at());
        userSettings.setProfile_visibility(updatedUserSettings.getProfile_visibility());
        userSettings.setFavorite_projects(updatedUserSettings.getFavorite_projects());
        userSettings.setNotification_preferences(updatedUserSettings.getNotification_preferences());

        UserSettings updatedEntity = userSettingsRepository.save(userSettings);
        return autoMapper.convertToDto(updatedEntity, UserSettingsDto.class);
    }

    @Override
    public UserSettingsDto createUserSettings(UserSettingsDto userSettingsDto) {
        UserSettings userSettings = autoMapper.convertToEntity(userSettingsDto, UserSettings.class);
        UserSettings savedUserSettings = userSettingsRepository.save(userSettings);
        return autoMapper.convertToDto(savedUserSettings, UserSettingsDto.class);
    }
}
