package com.filepackage.service;

import com.filepackage.dto.AdminActionsDto;

public interface IAdminActionsService<AdminActionsDto, Long> extends IBaseService<AdminActionsDto, Long> {
    AdminActionsDto createAdminAction(AdminActionsDto adminActionsDto);
}
