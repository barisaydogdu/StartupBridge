package com.filepackage.service;

import com.filepackage.dto.UserSettingsDto;
import com.filepackage.entity.UserSettings;

public interface IUserSettingsService <UserSettingsDto,Long> extends IBaseService<UserSettingsDto,Long>{
    UserSettingsDto createUserSettings(UserSettingsDto commentDto);
}
