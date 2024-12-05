package com.filepackage.service;

import com.filepackage.dto.CommunicationPreferenceDto;

public interface ICommunicationPreferenceService<CommunicationPreferenceDto, Long> extends IBaseService<CommunicationPreferenceDto, Long> {
    CommunicationPreferenceDto createCommunicationPreference(CommunicationPreferenceDto preferenceDto);
}
