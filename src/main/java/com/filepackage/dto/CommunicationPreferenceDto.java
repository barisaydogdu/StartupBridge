package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationPreferenceDto {

    private Long preferenceId;
    private Integer investorId;
    private String meetingPreference;
    private String contactChannels;
}
