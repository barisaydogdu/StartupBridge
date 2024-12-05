package com.filepackage.service;

import com.filepackage.dto.AdminActionDto;

import java.util.List;

public interface IAdminActionService {


    AdminActionDto getById(Long actionId);


    List<AdminActionDto> getAll();


    AdminActionDto createAdminAction(AdminActionDto adminActionDto);


    AdminActionDto update(Long actionId, AdminActionDto updatedAdminActionDto);


    void delete(Long actionId);
}
