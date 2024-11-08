package com.filepackage.service;

import com.filepackage.dto.CommunicationPreferencesDto;

public interface ICommunicationPreferencesService<CommunicationPreferencesDto, Long> extends IBaseService<CommunicationPreferencesDto, Long> {
    CommunicationPreferencesDto createCommunicationPreferences(CommunicationPreferencesDto communicationPreferencesDto);
}
