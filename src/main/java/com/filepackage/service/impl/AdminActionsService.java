package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.AdminActionsDto;
import com.filepackage.entity.AdminActions;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IAdminActionsRepository;
import com.filepackage.service.IAdminActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminActionsService implements IAdminActionsService<AdminActionsDto, Long> {

    @Autowired
    private AutoMapper autoMapper;

    private final IAdminActionsRepository adminActionsRepository;

    @Autowired
    public AdminActionsService(IAdminActionsRepository adminActionsRepository) {
        this.adminActionsRepository = adminActionsRepository;
    }

    @Override
    public AdminActionsDto getById(Long actionId) {
        AdminActions action = adminActionsRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Action does not exist with given id: " + actionId));
        return autoMapper.convertToDto(action, AdminActionsDto.class);
    }

    @Override
    public List<AdminActionsDto> getAll() {
        List<AdminActions> actions = adminActionsRepository.findAll();
        return actions.stream()
                .map(action -> autoMapper.convertToDto(action, AdminActionsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long actionId) {
        AdminActions action = adminActionsRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Action does not exist with given id: " + actionId));
        adminActionsRepository.deleteById(actionId);
    }

    @Override
    public AdminActionsDto update(Long actionId, AdminActionsDto updatedAction) {
        AdminActions action = adminActionsRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Action does not exist with given id: " + actionId));

        action.setAdmin_id(updatedAction.getAdmin_id());
        action.setAction_type(updatedAction.getAction_type());
        action.setTarget_id(updatedAction.getTarget_id());
        action.setAction_description(updatedAction.getAction_description());
        action.setAction_timestamp(updatedAction.getAction_timestamp());

        AdminActions updatedEntity = adminActionsRepository.save(action);
        return autoMapper.convertToDto(updatedEntity, AdminActionsDto.class);
    }

    @Override
    public AdminActionsDto createAction(AdminActionsDto actionDto) {
        AdminActions action = autoMapper.convertToEntity(actionDto, AdminActions.class);
        AdminActions savedAction = adminActionsRepository.save(action);
        return autoMapper.convertToDto(savedAction, AdminActionsDto.class);
    }
}
